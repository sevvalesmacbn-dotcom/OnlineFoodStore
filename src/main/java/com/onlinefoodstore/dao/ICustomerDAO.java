package com.onlinefoodstore.dao;
import com.onlinefoodstore.model.Customer;
public interface ICustomerDAO {
    boolean insertCustomerDetails(Customer customer);
    Customer getCustomerById(int id);
    boolean deleteCustomer(int id);
}
