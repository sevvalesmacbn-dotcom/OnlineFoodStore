package com.onlinefoodstore.dao;
import com.onlinefoodstore.model.Customer;

import java.util.List;

public interface ICustomerDAO {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    Customer getCustomerById(int id);
    Customer getCustomerByUsername(String username);
    Customer getCustomerByUserId(int userId);
    List<Customer> getAllCustomers();
    boolean deleteCustomer(int id);
}
