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

            System.out.println("Şifre: ");
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

}
