package com.onlinefoodstore.payment;

public class CashOnDeliveryPayment implements IPaymentMethod{
    @Override
    public void pay(double amount){
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("\uD83D\uDCB5 Kapıda Ödeme");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Tutar: "+String.format("%.2f TL",amount));
        System.out.println("✅ Kapıda ödeme secildi.Ödemeyi kurye geldiğinde yapabilirsiniz.");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
}
