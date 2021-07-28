package com.food.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DishesDTO {
    private List<DishDTO> dishes;
}
