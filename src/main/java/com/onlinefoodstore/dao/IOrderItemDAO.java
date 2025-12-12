package com.onlinefoodstore.dao;
import com.onlinefoodstore.model.OrderItem;
import java.util.List;
public interface IOrderItemDAO {
    boolean saveOrderItem(OrderItem item);
    List<OrderItem> getOrderItemsByOrderId(int orderId);
}
