package com.onlinefoodstore.dao.impl;

import com.onlinefoodstore.dao.ICustomerDAO;
import com.onlinefoodstore.dao.IOrderDAO;
import com.onlinefoodstore.dao.impl.OrderDAOImpl;
import com.onlinefoodstore.dao.impl.FoodDAOImpl;
import com.onlinefoodstore.model.Order;
import com.onlinefoodstore.model.OrderItem;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;;
public class OrderDAOImpl implements IOrderDAO{

    private Connection connection;
    private OrderItemDAOImpl orderItemDAO;
    private FoodDAOImpl foodDAO;
    private ICustomerDAO customerDAO;

    public OrderDAOImpl(Connection connection,ICustomerDAO customerDAO){
        this.connection=connection;
        this.customerDAO=customerDAO;
        this.orderItemDAO=new OrderItemDAOImpl(connection);
        this.foodDAO= new FoodDAOImpl(connection);
    }
    @Override
    public boolean saveOrder(Order order) {
        String sql="INSERT INTO orders (customer_İD, origİnal_total, discounted_total, used_coupon_code, order_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,order.getCustomer().getId());
            ps.setDouble(2,order.getOriginalTotal());
            ps.setDouble(3,order.getDiscountedTotal());
            ps.setString(4,order.getUsedCouponCode());
            ps.setTimestamp(5, Timestamp.valueOf(order.getOrderDate()));
            ps.setString(6,order.getStatus());
            int affected=ps.executeUpdate();
            if(affected>0){
                ResultSet rs=ps.getGeneratedKeys(); //olusan id'yi geri al
                if(rs.next()){
                    int newOrderId=rs.getInt(1);
                    order.setId(newOrderId); // order nesnesini set et demek
                }
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders =new ArrayList<>();
        String sql= "SELECT * FROM orders WHERE customer_id = ? ORDER BY order_date DESC";
        try(PreparedStatement stmt=connection.prepareStatement(sql)) {
            stmt.setInt(1,customerId);
            ResultSet rs= stmt.executeQuery();
            while (rs.next()){
                Order order= new Order(
                        customerDAO.getCustomerById(rs.getInt("customer_id")),
                        null,
                        rs.getDouble("original_total"),
                        rs.getDouble("discounted_total"),
                        rs.getString("used_coupon_code"),
                        rs.getTimestamp("order_date").toLocalDateTime(),
                        rs.getString("status")
                );
                order.setId(rs.getInt("id"));
                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }
}
