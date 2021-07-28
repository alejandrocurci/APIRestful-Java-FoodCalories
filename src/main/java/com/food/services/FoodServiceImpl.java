package com.food.services;

import com.food.dtos.CaloriesResponseDTO;
import com.food.exceptions.IngredientNotFound;
import com.food.repositories.IngredientRepository;
import com.food.dtos.IngredientDTO;
import com.food.dtos.IngredientResponseDTO;
import com.food.dtos.DishDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final IngredientRepository ingredientRepository;

    public FoodServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<IngredientResponseDTO> fetchAllIngredients(String order) {
        List<IngredientResponseDTO> list = ingredientRepository.loadDataBase();
        switch (order) {
            case "asc":
                list.sort((d1, d2) -> d1.getCalories() - d2.getCalories());
                break;
            case "desc":
                list.sort((d1, d2) -> d2.getCalories() - d1.getCalories());
                break;
            default:
                break;
        }
        return list;
    }

    @Override
    public List<CaloriesResponseDTO> calculateDishes(List<DishDTO> dishes) throws IngredientNotFound {
        List<CaloriesResponseDTO> dishesResponse = new ArrayList<>();
        for (DishDTO dish : dishes) {
            dishesResponse.add(this.calculateDishCalories(dish));
        }
        return dishesResponse;
    }

    @Override
    public CaloriesResponseDTO calculateDishCalories(DishDTO dish) throws IngredientNotFound {
        CaloriesResponseDTO response = new CaloriesResponseDTO();
        response.setTotalCalories(this.calculateCalories(dish));
        List<IngredientResponseDTO> ingredientsList = this.fetchIngredients(dish);
        response.setIngredientCalories(ingredientsList);
        response.setGreatestIngredient(findGreatestIngredient(ingredientsList));
        return response;
    }

    public int calculateCalories(DishDTO dish) throws IngredientNotFound {
        int calories = 0;
        for (IngredientDTO ingredient : dish.getIngredients()) {
            IngredientResponseDTO ingredientDTO = ingredientRepository.findCaloriesByName(ingredient.getIngredient());
            calories += ingredientDTO.getCalories() * ingredient.getWeight();
        }
        return calories;
    }

    public List<IngredientResponseDTO> fetchIngredients(DishDTO dish) throws IngredientNotFound {
        List<IngredientResponseDTO> ingredients = new ArrayList<>();
        for (IngredientDTO ingredient : dish.getIngredients()) {
            IngredientResponseDTO responseDTO = ingredientRepository.findCaloriesByName(ingredient.getIngredient());
            responseDTO.setCalories(responseDTO.getCalories() * ingredient.getWeight());
            ingredients.add(responseDTO);
        }
        return ingredients;
    }

    public IngredientResponseDTO findGreatestIngredient(List<IngredientResponseDTO> ingredientDTOs) {
        int max = 0;
        IngredientResponseDTO greatestIngredient = null;
        for (IngredientResponseDTO ingredient : ingredientDTOs) {
            if (ingredient.getCalories() > max) {
                max = ingredient.getCalories();
                greatestIngredient = ingredient;
            }
        }
        return greatestIngredient;
    }

}
