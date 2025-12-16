package com.onlinefoodstore.service.impl;
import com.onlinefoodstore.dao.IUserDAO;
import com.onlinefoodstore.dao.ICustomerDAO;
import com.onlinefoodstore.model.Customer;
import com.onlinefoodstore.model.User;
import com.onlinefoodstore.service.IUserService;
public class UserServiceImpl implements IUserService{
    private final IUserDAO userDAO;
    private final ICustomerDAO customerDAO;
    public UserServiceImpl(IUserDAO userDAO,ICustomerDAO customerDAO){
        this.userDAO=userDAO;
        this.customerDAO=customerDAO;
    }

    @Override
    public User login(String username, int password){
        User user=userDAO.getByUsername(username);
        if(user==null || user.getPassword() != password){
            return null;
        }
        return user;
    }
    @Override
    public boolean registerCustomer(Customer customer){
        if(!isUsernameAvailable(customer.getUsername())){
            return false;
        }
        boolean isUserAdded = userDAO.insert(customer);
        boolean isCustomerAdded = customerDAO.insertCustomerDetails(customer);
        return isUserAdded && isCustomerAdded;
    }
    @Override
    public boolean isUsernameAvailable(String username) {
        return userDAO.getByUsername(username)==null;
    }

}
