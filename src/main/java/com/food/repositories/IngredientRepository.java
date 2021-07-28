package com.food.repositories;

import com.food.dtos.IngredientDTO;
import com.food.exceptions.IngredientNotFound;
import com.food.dtos.IngredientResponseDTO;

import java.util.List;

public interface IngredientRepository {
    public List<IngredientResponseDTO> loadDataBase();
    public IngredientResponseDTO findCaloriesByName(String name) throws IngredientNotFound;
}
