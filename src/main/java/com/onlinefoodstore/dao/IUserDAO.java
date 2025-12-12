package com.onlinefoodstore.dao;
import com.onlinefoodstore.model.User;
public interface IUserDAO {
    User getByUsername(String username); //login icin gerekli
    User getById(int id); //kullanıcıyı id'ye göre getirir
    boolean insert(User user);
    boolean update(User user);
    boolean delete(int id);

}
