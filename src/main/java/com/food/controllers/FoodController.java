package com.food.controllers;

import com.food.exceptions.IngredientNotFound;
import com.food.dtos.ErrorDTO;
import com.food.dtos.DishDTO;
import com.food.dtos.DishesDTO;
import com.food.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/ingredients")
    public ResponseEntity fetchIngredients(@RequestParam (required = false, defaultValue = "") String order) {
        return new ResponseEntity(foodService.fetchAllIngredients(order), HttpStatus.OK);
    }

    @PostMapping("/calories")
    public ResponseEntity calculateCalories(@RequestBody DishDTO dish) throws IngredientNotFound {
        return new ResponseEntity(foodService.calculateDishCalories(dish), HttpStatus.OK);
    }

    @PostMapping("/dishes")
    public ResponseEntity obtenerListado(@RequestBody DishesDTO dishes) throws IngredientNotFound {
        return new ResponseEntity(foodService.calculateDishes(dishes.getDishes()), HttpStatus.OK);
    }

    @ExceptionHandler(IngredientNotFound.class)
    public ResponseEntity<ErrorDTO> handleException(IngredientNotFound e) {
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setName("Invalid Ingredient!");
        errorDto.setDescription("The ingredient "+e.getMessage()+" is invalid");
        return new ResponseEntity<ErrorDTO>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
