package com.onlinefoodstore.dao.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/onlinefoodstore?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    public static Connection getConnection(){
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           return DriverManager.getConnection(URL, USER, PASSWORD);
       } catch (ClassNotFoundException e) {
           System.out.println("❌ MySQL JDBC Driver bulunamadı!");
           e.printStackTrace();
       }catch (SQLException e){
           System.out.println("❌ Veritabanı bağlantısı başarısız!");
           e.printStackTrace();
       }
       return null;
    }
}
