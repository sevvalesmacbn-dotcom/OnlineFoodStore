package com.onlinefoodstore.dao.impl;

import com.onlinefoodstore.dao.IOrderItemDAO;
import com.onlinefoodstore.model.OrderItem;
import com.onlinefoodstore.model.Food;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class OrderItemDAOImpl implements IOrderItemDAO {
    private Connection connection;
    public OrderItemDAOImpl(Connection connection) {this.connection=connection;
    }

    @Override
    public boolean saveOrderItem(OrderItem item) {
        String sql="INSERT INTO orders_items (order_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";
        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,item.getOrder().getId());
            ps.setInt(2,item.getFood().getId());
            ps.setInt(3,item.getQuantity());
            ps.setDouble(4,item.getPrice());
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> items= new ArrayList<>();
        String sql="SELECT oi.*, b.name, b.restaurant, b.category FROM order_items oi JOIN foods b ON oi.food_id = b.id WHERE oi.order_id = ?";
        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,orderId);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                OrderItem item = new OrderItem();
                Food food = new Food();
                food.setId(rs.getInt("id"));
                food.setName(rs.getNString("name"));
                food.setRestaurant(rs.getNString("restaurant"));
                food.setCategory(rs.getNString("category"));
                item.setFood(food);
                item.setId(rs.getInt("id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}
