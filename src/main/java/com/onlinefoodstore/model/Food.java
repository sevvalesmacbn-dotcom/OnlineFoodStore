package com.onlinefoodstore.model;

public class Food {
    private int id;
    private String name;
    private String restaurant;
    private String category;
    private double price;
    private int stock;

    public Food(){}

    public Food(String name,String restaurant,String category,double price,int stock ){
        this.name=name;
        this.restaurant=restaurant;
        this.category=category;
        this.price=price;
        this.stock=stock;
    }
    public Food(int id,String name,String category,double price , int stock){
        this.id=id;
        this.name=name;
        this.category=category;
        this.price=price;
        this.stock=stock;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getRestaurant(){return restaurant;}
    public void setRestaurant(String restaurant){this.restaurant=restaurant;}

    public String getCategory(){return category;}
    public void setCategory(String category){this.category=category;}

    public double getPrice(){return price;}
    public void setPrice(double price){this.price=price;}

    public int getStock(){return stock;}
    public void setStock(int stock){this.stock=stock;}
    
}
