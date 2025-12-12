package com.onlinefoodstore.dao;
import com.onlinefoodstore.model.Food;
import java.util.List;
public interface IFoodDAO {
    List<Food> getAllFoods();
    Food getFoodById(int id);
    boolean addFood(Food food);
    boolean updateFood(Food food);
    boolean deleteFood(int id);
    boolean decreaseStock(int foodId,int quantity);
    boolean updateFoodStock(int id,int newStock);
}
