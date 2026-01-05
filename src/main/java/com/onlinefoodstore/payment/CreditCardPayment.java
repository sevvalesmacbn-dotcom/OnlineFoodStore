package com.onlinefoodstore.payment;

import java.util.Scanner;

public class CreditCardPayment implements IPaymentMethod {

    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("💳 Kredi Kartı ile Ödeme");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            System.out.print("Kart Sahibi Ad Soyad: ");
            String cardHolderName = scanner.nextLine();

            System.out.print("Kart Numarası (16 haneli): ");
            String cardNumber = scanner.nextLine();

            if (!cardNumber.matches("\\d{16}")) {
                throw new IllegalArgumentException("Kart numarası 16 haneli ve sadece rakamlardan oluşmalıdır.");
            }

            System.out.print("Son Kullanma Tarihi (AA/YY): ");
            String expiryDate = scanner.nextLine();

            System.out.print("CVV (3 haneli): ");
            String cvv = scanner.nextLine();

            if (!cvv.matches("\\d{3}")) {
                throw new IllegalArgumentException("CVV 3 haneli ve sadece rakamlardan oluşmalıdır.");
            }

            // Ödeme Özeti
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("📄 Ödeme Özeti");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("Kart Sahibi : " + cardHolderName);
            System.out.println("Kart No     : **** **** **** " + cardNumber.substring(12));
            System.out.println("Tutar       : " + String.format("%.2f TL", amount));

            System.out.println("\n✅ Kredi kartı ödemesi başarıyla gerçekleştirildi!");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Ödeme iptal edildi!");
            System.out.println("Hata: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("\n❌ Beklenmeyen bir hata oluştu!");
            System.out.println("Ödeme iptal edildi.");
        }
    }
}
