package com.onlinefoodstore.service.impl;
import com.onlinefoodstore.dao.IFoodDAO;
import com.onlinefoodstore.model.Food;
import com.onlinefoodstore.service.IFoodService;
import java.util.List;
public class FoodServiceImpl implements IFoodService {
    private final IFoodDAO foodDAO;
    public FoodServiceImpl (IFoodDAO foodDAO){this.foodDAO=foodDAO;}

    @Override
    public List<Food> getAllFoods(){return foodDAO.getAllFoods();}

    @Override
    public Food getFoodById(int id){return foodDAO.getFoodById(id);}

    @Override
    public boolean addFood(Food food){
        if(food.getStock()<0 || food.getPrice()<=0){
            return false;
        }
        return foodDAO.addFood(food);
    }
    @Override
    public boolean updateFood(Food food){
        if (food.getStock()<0 || food.getPrice()<=0){
            return false;
        }
        return foodDAO.updateFood(food);
    }
    @Override
    public boolean deleteFood(int id){return foodDAO.deleteFood(id);}
    @Override
    public boolean updateFoodStock(Food food){
        return foodDAO.updateFoodStock(food.getId(),food.getStock());
    }
}
