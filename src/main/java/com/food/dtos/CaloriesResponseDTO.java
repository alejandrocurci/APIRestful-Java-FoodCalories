package com.food.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CaloriesResponseDTO {
    @JsonProperty("total_calories")
    private int totalCalories;
    @JsonProperty("ingredient_calories")
    private List<IngredientResponseDTO> ingredientCalories;
    @JsonProperty("greatest_ingredient")
    private IngredientResponseDTO greatestIngredient;
}
