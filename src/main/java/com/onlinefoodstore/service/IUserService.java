package com.onlinefoodstore.service;

import com.onlinefoodstore.model.Customer;
import com.onlinefoodstore.model.User;

public interface IUserService {
    User login(String username, int password);

    boolean registerCustomer(Customer customer);

    boolean isUsernameAvailable(String username);
}
