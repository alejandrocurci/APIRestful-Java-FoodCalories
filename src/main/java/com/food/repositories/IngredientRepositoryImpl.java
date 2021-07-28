package com.food.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.dtos.IngredientDTO;
import com.food.exceptions.IngredientNotFound;
import com.food.dtos.IngredientResponseDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {


    @Override
    public IngredientResponseDTO findCaloriesByName(String name) throws IngredientNotFound {
        List<IngredientResponseDTO> ingredientsDTOs = null;
        ingredientsDTOs = loadDataBase();
        IngredientResponseDTO result = null;
        if (ingredientsDTOs != null) {
            Optional<IngredientResponseDTO> item = ingredientsDTOs.stream()
                    .filter(ingredientResponseDTO -> ingredientResponseDTO.getName().equals(name))
                    .findFirst();
            if (item.isPresent())
                result = item.get();
            else
                throw new IngredientNotFound(name);
        }
        return result;
    }

    @Override
    public List<IngredientResponseDTO> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:food.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<IngredientResponseDTO>> typeReference = new TypeReference<List<IngredientResponseDTO>>() {
        };
        List<IngredientResponseDTO> ingredients = null;

        try {
            ingredients = objectMapper.readValue(file, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingredients;
    }
}
