package com.onlinefoodstore.model;

public class Coupon {
    private int id;
    private String code;
    private double discountAmount;

    public Coupon(){}

    public Coupon(String code,double discountAmount){
        this.code=code;
        this.discountAmount=discountAmount;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public String getCode(){return code;}
    public void setCode(String code){this.code=code;}

    public double getDiscountAmount(){return discountAmount;}
    public void setDiscountAmount(double discountAmount){this.discountAmount=discountAmount;}
}
