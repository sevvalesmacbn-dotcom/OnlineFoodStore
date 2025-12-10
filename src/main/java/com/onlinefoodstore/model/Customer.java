package com.onlinefoodstore.model;

public class Customer extends User {
    private String phone;
    private String address;

    public Customer(){
        super();
    }
    public Customer(String name, String username, int password, String role ,String phone ,String address){
        super(name,username,password,role);
        this.phone=phone;
        this.address=address;
    }
    public String getPhone(){ return phone;}
    public void setPhone(String phone){ this.phone=phone;}

    public String getAddress(){ return address;}
    public void setAddress(String address){ this.address=address;}



}
