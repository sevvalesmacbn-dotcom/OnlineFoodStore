package com.onlinefoodstore.service;

import com.onlinefoodstore.model.Food;

import java.util.List;

public interface IFoodService {
    List<Food> getAllFoods();

    Food getFoodById(int id);

    boolean addFood(Food food);

    boolean updateFood(Food food);

    boolean deleteFood(int id);

    boolean updateFoodStock(Food food);
}
