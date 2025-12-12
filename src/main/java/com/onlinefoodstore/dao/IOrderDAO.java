package com.onlinefoodstore.dao;
import com.onlinefoodstore.model.Order;
import java.util.List;
public interface IOrderDAO {
    boolean saveOrder(Order order);
    Order getOrderById(int id);
    List<Order> getOrdersByCustomerId(int customerId);
}
