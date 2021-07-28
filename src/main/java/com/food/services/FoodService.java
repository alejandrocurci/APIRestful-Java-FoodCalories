package com.food.services;

import com.food.dtos.IngredientDTO;
import com.food.dtos.IngredientResponseDTO;
import com.food.exceptions.IngredientNotFound;
import com.food.dtos.CaloriesResponseDTO;
import com.food.dtos.DishDTO;

import java.util.List;

public interface FoodService {
    public List<IngredientResponseDTO> fetchAllIngredients(String order);

    public CaloriesResponseDTO calculateDishCalories(DishDTO dish) throws IngredientNotFound;

    public List<CaloriesResponseDTO> calculateDishes(List<DishDTO> dishes) throws IngredientNotFound;

}
