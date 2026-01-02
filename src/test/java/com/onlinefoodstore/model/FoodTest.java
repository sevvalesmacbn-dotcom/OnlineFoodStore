package com.onlinefoodstore.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Food model sınıfı icin basit test

public class FoodTest {

    @Test
    public void testFoodConstructorAndGetters() {
        // Arrange & Act
        Food food = new Food(1, "Lahmacun", "Öz Urfa", "Ana Yemek", 45.0, 10);

        // Assert
        assertEquals(1, food.getId());
        assertEquals("Lahmacun", food.getName());
        assertEquals("Öz Urfa", food.getRestaurant());
        assertEquals("Ana Yemek", food.getCategory());
        assertEquals(45.0, food.getPrice());
        assertEquals(10, food.getStock());
    }

    @Test
    public void testFoodSetters(){
        // Arrange
        Food food = new Food();

        // Act
        food.setId(2);
        food.setName("İskender");
        food.setRestaurant("Kebapcı İskender");
        food.setCategory("Ana Yemek");
        food.setPrice(120.0);
        food.setStock(5);

        // Assert
        assertEquals(2, food.getId());
        assertEquals("İskender", food.getName());
        assertEquals("Kebapcı İskender", food.getRestaurant());
        assertEquals("Ana Yemek", food.getCategory());
        assertEquals(120.0, food.getPrice());
        assertEquals(5, food.getStock());
    }

    @Test
    public void testFoodEmptyConstructor(){
        // Act
        Food food = new Food();

        // Assert
        assertNotNull(food);
        assertEquals(0,food.getId());
        assertNull(food.getName());
    }

    @Test
    public void testFoodPriceCanBeZero(){
        // Arrange & Act
        Food food = new Food(3, "Bedava Çorba", "Hayır Lokantası", "Çorba", 0.0, 100);

        // Assert
        assertEquals(0.0, food.getPrice());
    }
}

