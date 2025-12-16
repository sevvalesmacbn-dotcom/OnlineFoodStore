package com.onlinefoodstore.service.impl;

import com.onlinefoodstore.dao.IOrderDAO;
import com.onlinefoodstore.model.Order;
import com.onlinefoodstore.service.IOrderService;

import java.util.List;

public class OrderServiceImpl implements IOrderService {
    private final IOrderDAO orderDAO;

    public OrderServiceImpl(IOrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderDAO.getOrdersByCustomerId(customerId);
    }
}
