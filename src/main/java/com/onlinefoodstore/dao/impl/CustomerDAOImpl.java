package com.onlinefoodstore.dao.impl;

import com.onlinefoodstore.dao.ICustomerDAO;
import com.onlinefoodstore.model.Customer;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class CustomerDAOImpl implements ICustomerDAO {
    private Connection connection;
    public CustomerDAOImpl(Connection connection){this.connection=connection;}

    @Override
    public boolean addCustomer(Customer customer) {
        String sql="INSERT INTO users (name, username, password, role, phone, address) VALUES(?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getUsername());
            ps.setInt(3,customer.getPassword());
            ps.setString(4,customer.getRole());
            ps.setString(5,customer.getPhone());
            ps.setString(6,customer.getAddress());

            int affected= ps.executeUpdate();

            if(affected>0){
                ResultSet rs= ps.getGeneratedKeys();
                if (rs.next()){
                    customer.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean updateCustomer(Customer customer) {
        String sql="UPDATE users SET name = ?, username = ?, password = ?, role = ?, phone = ?, address = ?, WHERE id = ?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2,customer.getUsername());
            ps.setInt(3,customer.getPassword());
            ps.setString(4,customer.getRole());
            ps.setString(5,customer.getPhone());
            ps.setString(6,customer.getAddress());
            ps.setInt(7,customer.getId());

            return ps.executeUpdate()>0;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql= "SELECT * FROM users WHERE id = ? AND role = 'CUSTOMER'";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
               Customer customer = new Customer();
               customer.setId(rs.getInt("id"));
               customer.setName(rs.getString("name"));
               customer.setUsername(rs.getString("username"));
               customer.setPassword(rs.getInt("password"));
               customer.setRole(rs.getString("role"));
               customer.setPhone(rs.getString("phone"));
               customer.setAddress(rs.getString("address"));
               return customer;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        String sql= """
                SELECT
                    u.id,
                    u.name,
                    u.username,
                    u.password,
                    u.role,
                    c.phone,
                    c.address
                FROM users u
                JOIN customers c ON u.id = c.id
                WHERE u.username = ?
            """;
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getInt("password"));
                customer.setRole(rs.getString("role"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                return customer;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCustomerByUserId(int userId) {
        String sql= """
                SELECT
                    u.id,
                    u.name,
                    u.username,
                    u.password,
                    u.role,
                    c.phone,
                    c.address
                FROM users u
                JOIN customers c ON u.id = c.id
                WHERE u.id = ?
            """;
        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getInt("password"));
                customer.setRole(rs.getString("role"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                return customer;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(Statement stmt = connection.createStatement();
             ResultSet rs=stmt.executeQuery(sql)){

            while (rs.next()){
               Customer customer =  new Customer();
               customer.setId(rs.getInt("id"));
               customer.setName(rs.getString("name"));
               customer.setUsername(rs.getString("username"));
               customer.setPassword(rs.getInt("password"));
               customer.setRole(rs.getString("role"));
               customer.setPhone(rs.getString("phone"));
               customer.setAddress(rs.getString("address"));
               customers.add(customer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean deleteCustomer(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}


