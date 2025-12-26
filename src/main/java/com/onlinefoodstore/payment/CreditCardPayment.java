package com.onlinefoodstore.payment;

public class CreditCardPayment implements IPaymentMethod{
    @Override
    public void pay(double amount) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("\uD83D\uDCB3 Kredi Kartı ile Ödeme");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Tutar: "+String.format("%.2f TL",amount));
        System.out.println("✅ Kredi kartı ödemesi başarıyla gerçekleştirildi! ");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
}
