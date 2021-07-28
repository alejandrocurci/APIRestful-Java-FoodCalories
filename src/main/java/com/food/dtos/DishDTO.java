package com.food.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DishDTO {
    private String dish;
    private List<IngredientDTO> ingredients;
}
