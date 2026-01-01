package com.onlinefoodstore.console;
import com.onlinefoodstore.dao.ICouponDAO;
import com.onlinefoodstore.dao.ICustomerDAO;
import com.onlinefoodstore.dao.IFoodDAO;
import com.onlinefoodstore.dao.IUserDAO;
import com.onlinefoodstore.dao.connection.DBConnection;
import com.onlinefoodstore.dao.impl.CouponDAOImpl;
import com.onlinefoodstore.dao.impl.CustomerDAOImpl;
import com.onlinefoodstore.dao.impl.FoodDAOImpl;
import com.onlinefoodstore.dao.impl.UserDAOImpl;
import com.onlinefoodstore.model.Coupon;
import com.onlinefoodstore.model.Customer;
import com.onlinefoodstore.model.Food;
import com.onlinefoodstore.model.User;
import com.onlinefoodstore.payment.CashOnDeliveryPayment;
import com.onlinefoodstore.payment.CreditCardPayment;
import com.onlinefoodstore.payment.IPaymentMethod;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
**ONLİNE FOOD STORE CONSOLE APPLICATION**
Kullanıcı giris yapabilir yemekleri görüntüleyebilir
secim yapabilir ve ödeme yöntemini belirleyebilir
 */
public class FoodStoreConsoleApp {

    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection;
    private static IUserDAO userDAO;
    private static IFoodDAO foodDAO;
    private static ICouponDAO couponDAO;
    private static ICustomerDAO customerDAO;
    private static User currentUser;
    private static Customer currentCustomer;
    private static List<Food>selectedFoods = new ArrayList<>();

    public static void main(String[] args){
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      Online Food Store Console App     ║");
        System.out.println("╚════════════════════════════════════════╝");
        // veritabanı baglantısı kurma
        connection = DBConnection.getConnection();
        if (connection == null){
            System.out.println("❌ Veritabanı bağlantısı kurulamadı. Program sonlandırılıyor...");
            return;
        }
        // DAOları baslatma
        userDAO = new UserDAOImpl(connection);
        foodDAO = new FoodDAOImpl(connection);
        couponDAO = new CouponDAOImpl(connection);
        customerDAO = new CustomerDAOImpl(connection);
        //login islemi
        if (!loginUser()){
            System.out.println("❌ Giriş başarısız.Program sonlandırılıyor...");
            closeConnection();
            return;
        }
        //ANA MENÜ
        boolean running = true;
        while (running){
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║           ANA MENÜ                     ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Yemekleri Listele                   ║");
            System.out.println("║ 2. Sepeti Görüntüle                    ║");
            System.out.println("║ 3. Ödeme Yap                           ║");
            System.out.println("║ 4. Çıkış                               ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Seciminiz: ");
            int choice = getIntInput();
            switch (choice){
                case 1:
                    listAndSelectFoods();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    checkout();
                    break;
                case 4:
                    running = false;
                    System.out.println("\uD83D\uDC4B\n Çıkış yapılıyor. Güle güle!");
                    break;
                default:
                    System.out.println("❌ Geçersiz seçim! Lütfen 1-4 arası bir değer girin.");
            }
        }
        closeConnection();
    }
    //Kullanıcı login islemii
    private static boolean loginUser() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("         KULLANICI GİRİŞİ");
        System.out.println("═══════════════════════════════════════");
        for(int attempt =0;attempt <3; attempt++){
            System.out.print("Kullanıcı Adı: ");
            String username = scanner.nextLine().trim();

            System.out.print("Şifre: ");
            int password = getIntInput();
            // veritabanından kullanıcıyı getir
            User user = userDAO.getByUsername(username);
            if (user != null && user.getPassword() == password){
                currentUser = user;
                // customer bilgilerini cek (varsa)
                currentCustomer = customerDAO.getCustomerByUsername(username);
                if (currentCustomer != null){
                    System.out.println("\n✅ Giriş başarılı! Hoş geldiniz, "+ currentCustomer.getName());
                    System.out.println("\uD83D\uDCF1\n Telefon: "+(currentCustomer.getPhone() != null ? currentCustomer.getPhone() : "Bilgi yok"));
                    System.out.println("\uD83D\uDCCD\n Adres: "+(currentCustomer.getAddress() != null ? currentCustomer.getAddress(): "Bilgi yok"));
                }else {
                    System.out.println("\n✅ Giriş başarılı! Hoş geldiniz, "+user.getName());
                }
                return true;
            }else {
                System.out.println("❌ Kullanıcı adı veya şifre hatalı! Kalan deneme: "+(2-attempt));
            }
        }
        return false;
    }
        //Yemekleri liste ve kullanıcının secim yapmasını sagla
    private static void listAndSelectFoods(){
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                          YEMEK LİSTESİ                                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");

        List<Food> foods = foodDAO.getAllFoods();

        if (foods.isEmpty()){
            System.out.println("❌ Sistemde yemek bulunmamaktadır.");
            return;
        }
        //Yemekleri listele
        System.out.println(String.format("%-5s %-30s %-20s %-15s %-10s %-10s",
                "ID","Ad","Restoran","Kategori","Fiyat","Stok"));
        System.out.println("─".repeat(95));
        for (Food food : foods){
            System.out.println(String.format("%-5d %-30s %-20s %-15s %-10.2f %-10d",
                    food.getId(),
                    food.getName().length()>30 ? food.getName().substring(0, 27) +"...": food.getName(),
                    food.getRestaurant().length()>20 ? food.getRestaurant().substring(0, 17) +"...": food.getRestaurant(),
                    food.getCategory(),
                    food.getPrice(),
                    food.getStock()));
        }
        //Yemek seçimi
        System.out.println("\n───────────────────────────────────────────────────────────────────────────");
        System.out.print("Sepete eklemek için yemek ID'si girin (0 = Ana menüye dön): ");
        int foodId = getIntInput();

        if (foodId == 0){
            return;
        }
        Food selectedFood = foodDAO.getFoodById(foodId);
        if (selectedFood == null){
            System.out.println("❌ Girilen ID'ye sahip yemek bulunamadı!");
            return;
        }
        if (selectedFood.getStock()<=0){
            System.out.println("❌ Bu yemek stokta yok!");
            return;
        }
        selectedFoods.add(selectedFood);
        System.out.println("✅ '"+selectedFood.getName()+"' sepete eklendi!");
    }
    // sepetteki yemekleri görüntüle
    private static void viewCart(){
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                          SEPETİNİZ                                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        if (selectedFoods.isEmpty()){
            System.out.println("❌ Sepetiniz boş!");
            return;
        }
        double total = 0;
        System.out.println(String.format("%-5s %-30s %-20s %-10s", "Sıra", "Yemek", "Restoran", "Fiyat"));
        System.out.println("─".repeat(70));
        for(int i=0;i<selectedFoods.size();i++){
            Food food =selectedFoods.get(i);
            System.out.println(String.format("%-5d %-30s %-20s %-10.2f TL",
                    (i+1),
                    food.getName().length()>30 ? food.getName().substring(0, 27)+"..." : food.getName(),
                    food.getRestaurant().length()>20 ? food.getRestaurant().substring(0, 17)+"..." : food.getRestaurant(),
                    food.getPrice()));
            total += food.getPrice();
        }
        System.out.println("─".repeat(70));
        System.out.println(String.format("%56s %.2f TL", "TOPLAM:", total));
    }
    //ödeme islemi
    private static void checkout(){
        if (selectedFoods.isEmpty()){
            System.out.println("❌ Sepetiniz boş! Önce yemek seçin.");
            return;
        }
        // Toplam tutarı hesapla
        double originalTotal = 0;
        for (Food food : selectedFoods){
            originalTotal += food.getPrice();
        }
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         ÖDEME İŞLEMİ                   ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Toplam Tutar: "+ String.format("%.2f TL",originalTotal));

        //Kupon kodu sorgusu
        System.out.println("\n───────────────────────────────────────");
        System.out.println("İndirim kuponu kullanmak ister misiniz? (E/H): ");
        String useCoupon = scanner.nextLine().trim().toUpperCase();

        double discountedTotal = originalTotal;
        String appliedCouponCode = null;

        if (useCoupon.equals("E")) {
            System.out.print("Kupon kodunu girin: ");
            String couponCode = scanner.nextLine().trim();

            Coupon coupon = couponDAO.getCouponByCode(couponCode);

            if (coupon != null) {
                discountedTotal = originalTotal - coupon.getDiscountAmount();
                if (discountedTotal < 0) {
                    discountedTotal = 0;
                }
                appliedCouponCode = coupon.getCode();
                System.out.println("✅ Kupon uygulandı! İndirim: " + String.format("%.2f TL", coupon.getDiscountAmount()));
                System.out.println("Yeni Toplam: " + String.format("%.2f TL", discountedTotal));
            } else {
                System.out.println("❌ Geçersiz kupon kodu!");
            }
        }
        // Ödeme yöntemi secimi
        System.out.println("\n───────────────────────────────────────");
        System.out.println("Ödeme Yöntemi Seçiniz:");
        System.out.println("1. Kredi Kartı");
        System.out.println("2. Kapıda Ödeme");
        System.out.println("───────────────────────────────────────");
        System.out.println("Seçiminiz: ");

        int paymentChoice = getIntInput();

        IPaymentMethod paymentMethod = null;

        switch (paymentChoice){
            case 1:
                paymentMethod = new CreditCardPayment();
                break;
            case 2:
                paymentMethod = new CashOnDeliveryPayment();
                break;
            default:
                System.out.println("❌ Geçersiz ödeme yöntemi!");
                return;
        }
        // ödemeyi işle
        System.out.println("\n💳 Ödeme işleniyor...");
        paymentMethod.pay(discountedTotal);
        // Stok güncelleme
        for (Food food : selectedFoods){
            foodDAO.decreaseStock(food.getId(), 1);
        }
        // sipariş özeti
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              ✅ SİPARİŞ BAŞARIYLA TAMAMLANDI!                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        System.out.println("\n📋 SİPARİŞ BİLGİLERİ:");
        System.out.println("───────────────────────────────────────────────────────────────");
        System.out.println("\uD83D\uDC64 Müşteri Adı    :"+ currentUser.getName());

        //customer bilgilerini göster
        if (currentCustomer != null){
            System.out.println("📱 Telefon         :"+ currentCustomer.getPhone());
            System.out.println("📍 Teslimat Adresi:"+currentCustomer.getAddress());
        }else {
            System.out.println("📱 Telefon        : Bilgi yok");
            System.out.println("📍 Teslimat Adresi: Bilgi yok");
        }
        System.out.println("───────────────────────────────────────────────────────────────");

        if (appliedCouponCode != null) {
            System.out.println("🎟️  Kullanılan Kupon : " + appliedCouponCode);
            System.out.println("💰 Tasarruf Edilen   : " + String.format("%.2f TL", (originalTotal - discountedTotal)));
            System.out.println("───────────────────────────────────────────────────────────────");
        }

        System.out.println("💵 Toplam Ödenen     : " + String.format("%.2f TL", discountedTotal));
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("📦 Yemekleriniz en kısa sürede belirtilen adrese teslim edilecektir.");
        System.out.println("🙏 Siparişiniz için teşekkür ederiz!");

        // Sepeti temizle
        selectedFoods.clear();
    }

    /**
     * Scanner'dan güvenli int alma
     */
    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Lütfen geçerli bir sayı girin: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Buffer temizle
        return value;
    }

    /**
     * Veritabanı bağlantısını kapat
     */
    private static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Veritabanı bağlantısı kapatıldı.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
