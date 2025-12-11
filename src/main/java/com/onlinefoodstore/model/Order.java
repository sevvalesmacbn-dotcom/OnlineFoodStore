package com.onlinefoodstore.model;
import java.time.LocalDateTime;
import java.util.List;
public class Order {
    private int id;
    private Customer customer;
    private List<OrderItem> items;
    private double originalTotal;
    private double discountedTotal;
    private String usedCouponCode;
    private LocalDateTime orderDate;
    private String status;


    public Order(){}

    public Order(Customer customer,List<OrderItem> items,double originalTotal,double discountdTotal,String usedCouponCode,LocalDateTime orderDate,String status){
        this.customer=customer;
        this.items=items;
        this.originalTotal=originalTotal;
        this.usedCouponCode=usedCouponCode;
        this.orderDate=orderDate;
        this.status=status;
    }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public Customer getCustomer(){return customer;}
    public void setCustomer(Customer customer){this.customer=customer;}

    public List<OrderItem> getItems(){return items;}
    public void setItems(List<OrderItem> items){this.items=items;}

    public double getOriginalTotal(){return originalTotal;}
    public void setOriginalTotal(double originalTotal){this.originalTotal=originalTotal;}

    public double getDiscountedTotal() { return discountedTotal; }
    public void setDiscountedTotal(double discountedTotal) { this.discountedTotal = discountedTotal; }

    public String getUsedCouponCode() { return usedCouponCode; }
    public void setUsedCouponCode(String usedCouponCode) { this.usedCouponCode = usedCouponCode; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void calculateOriginalTotal() {
        this.originalTotal = items.stream().mapToDouble(OrderItem::getPrice).sum();
    }

}
