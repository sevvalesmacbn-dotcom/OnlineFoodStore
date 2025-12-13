package com.onlinefoodstore.dao.impl;

import com.onlinefoodstore.dao.IFoodDAO;
import com.onlinefoodstore.model.Food;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAOImpl implements IFoodDAO {
    private Connection connection;

    public FoodDAOImpl(Connection connection){this.connection=connection;}
    @Override
    public List<Food> getAllFoods() {
        List<Food> foods=new ArrayList<>();
        String sql="SELECT * FROM foods";
        try(PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery()){
            while(rs.next()){
                Food food = new Food();
                food.setId(rs.getInt("id"));
                food.setCategory(rs.getNString("category"));
                food.setName(rs.getNString("name"));
                food.setPrice(rs.getDouble("price"));
                food.setStock(rs.getInt("stock"));
                food.setRestaurant(rs.getNString("restaurant"));
                food.add(food);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return foods;
    }

    @Override
    public Food getFoodById(int id) {
        String sql ="SELECT * FROM foods WHERE id =?";
        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Food food=new Food();
                food.setId(rs.getInt("id"));
                food.setRestaurant(rs.getNString("restaurant"));
                food.setStock(rs.getInt("stock"));
                food.setName(rs.getNString("name"));
                food.setPrice(rs.getDouble("price"));
                food.setCategory(rs.getNString("category"));
                return food;
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addFood(Food food) {
        String sql="INSERT INTO foods(name, restaurant, category, price, stock) VALUES(?, ?, ?, ?, ?)";
        try(PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setString(1,food.getName());
            ps.setString(2,food.getRestaurant());
            ps.setString(3,food.getCategory());
            ps.setDouble(4,food.getPrice());
            ps.setInt(5,food.getStock());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateFood(Food food) {
        String sql= "UPDATE foods SET name=?, restaurant=?, category=?, price=?, stock=? WHERE id=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setString(1,food.getName());
            ps.setString(2,food.getRestaurant());
            ps.setString(3,food.getCategory());
            ps.setDouble(4,food.getPrice());
            ps.setInt(5,food.getStock());
            ps.setInt(6,food.getId());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteFood(int id) {
        String sql="DELETE FROM foods WHERE id=?";
        try (PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1, id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean decreaseStock(int foodId, int quantity) {
        String sql="UPDATE foods SET stock=stock - ? WHERE id = ? AND stock >= ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, foodId);
            ps.setInt(3, quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateFoodStock(int id, int newStock) { String sql = "UPDATE books SET stock = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, newStock);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
