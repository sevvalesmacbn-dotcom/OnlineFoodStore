package com.onlinefoodstore.service;
import com.onlinefoodstore.model.Order;
import java.util.List;
public interface IOrderService {
    List<Order> getOrdersByCustomerId(int customerId);
}
