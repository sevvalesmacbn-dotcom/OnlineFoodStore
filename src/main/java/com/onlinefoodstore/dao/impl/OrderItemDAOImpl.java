package com.onlinefoodstore.dao.impl;

import java.sql.Connection;

public class OrderItemDAOImpl {
    private Connection connection;
    public OrderItemDAOImpl(Connection connection) {this.connection=connection;
    }
}
