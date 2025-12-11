package com.onlinefoodstore.model;

public class OrderItem {
    private int id;
    private Order order;
    private Food food;
    private int quantity;
    private double price;
    public OrderItem(){}

    public OrderItem(Order order,Food food,int quantity,double price){
        this.order=order;
        this.food=food;
        this.quantity=quantity;
        this.price=price;
    }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public Order getOrder(){return order;}
    public void setOrder(Order order){this.order=order;}

    public Food getFood(){return food;}
    public void setFood(Food food){this.food=food;}

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}

    public double getPrice(){return price;}
    public void setPrice(double price){this.price=price;}

    public void updatePrice(){
        if(food !=null){
            this.price=food.getPrice()*this.quantity;
        }
    }








}
