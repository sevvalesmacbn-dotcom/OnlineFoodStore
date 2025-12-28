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

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      Online Food Store Console App     ║");
        System.out.println("╚════════════════════════════════════════╝");
        // veritabanı baglantısı kurma
        connection = DBConnection.getConnection();
        if (connection == null) {
            System.out.println("❌ Veritabanı bağlantısı kurulamadı. Program sonlandırılıyor...");
            return;
        }
        // DAOları baslatma
        userDAO = new UserDAOImpl(connection);
        foodDAO = new FoodDAOImpl(connection);
        couponDAO = new CouponDAOImpl(connection);
        customerDAO = new CustomerDAOImpl(connection);
        //login islemi
        if (!loginUser()) {
            System.out.println("❌ Giriş başarısız.Program sonlandırılıyor...");
            closeConnection();
            return;
        }
    }
}
