package com.onlinefoodstore.dao.impl;

import com.onlinefoodstore.dao.ICustomerDAO;
import com.onlinefoodstore.model.Customer;
import java.sql.*;

public class CustomerDAOImpl implements ICustomerDAO {
    private Connection connection;
    public CustomerDAOImpl(Connection connection){this.connection=connection;}
    @Override
    public boolean insertCustomerDetails(Customer customer) {
        String sql="INSERT INTO customers (id, phone, address) VALUES (?, ?, ?)";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1,customer.getId());
            ps.setString(2,customer.getPhone());
            ps.setString(3,customer.getAddress());
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql="SELECT u.id, u.name, u.username, u.password, u.role, "+
                "c.phone, c.address FROM customers c " +
                "JOIN users u ON c.id=u.id WHERE c.id = ?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Customer customer=new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getNString("name"));
                customer.setUsername(rs.getNString("username"));
                customer.setPassword(rs.getInt("password"));
                customer.setRole(rs.getNString("role"));
                customer.setPhone(rs.getNString("phone"));
                customer.setAddress(rs.getNString("address"));
                return customer;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(int id) {
        String sql="DELETE FROM customers WHERE id = ?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
