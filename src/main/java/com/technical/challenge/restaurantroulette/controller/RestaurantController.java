package com.technical.challenge.restaurantroulette.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technical.challenge.restaurantroulette.entity.Restaurant;
import com.technical.challenge.restaurantroulette.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurant", description = "restaurant roulette api")
@RestController
@RequestMapping("restaurant")
@CrossOrigin()
public class RestaurantController {
	
	@Autowired
	private RestaurantService service;
	
	@Operation(
            summary = "Create Restaurant",
            description = "creates a new restaurant record in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully created restaurant record."),
            @ApiResponse(responseCode = "409", description = "Not able to create record as a restaurant with same name already exist in DB.")
    })
	@PostMapping("/addRestaurant")
	public ResponseEntity<?> createRestaurant(@RequestBody Restaurant restaurant){
		boolean success = service.createRestaurant(restaurant);
		if(success)
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Restaurant name is duplicated!", HttpStatus.CONFLICT);
	}
	
	@Operation(
            summary = "Delete All Restaurants",
            description = "Delete all restaurant records in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete is successful"),
            @ApiResponse(responseCode = "500", description = "Error retrieving restaurant from database.")
    })
	@DeleteMapping("/deleteAllRestaurantRecord")
	public ResponseEntity<?> deleteAllRestaurantRecord(){
		try {
			List<Restaurant> result = service.deleteAllRestaurantRecord();
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>("Error Deleting Selected Restaurant. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	@Operation(
            summary = "Retrieve All Restaurants",
            description = "Retrieve all restaurant records in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieve is successful"),
            @ApiResponse(responseCode = "500", description = "Error retrieving restaurant from database.")
    })
	@GetMapping("/getAllRestaurantRecord")
	public ResponseEntity<List<Restaurant>> getAllRestaurantRecord(){
		List<Restaurant> restaurantList = service.getAllRestaurantRecord();
		return new ResponseEntity<>(restaurantList, HttpStatus.OK);
	}
	
	@Operation(
            summary = "Randomly select and return a restaurant from database",
            description = "Retrieve all restaurant records in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully retrieval and return a restaurant record"),
            @ApiResponse(responseCode = "500", description = "Error retrieving restaurant from database.")
    })
	@GetMapping("/decideRestaurant")
	public ResponseEntity<Restaurant> decideRestaurant(){
		Restaurant restaurant = service.getRandomRestaurant();
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@Operation(
            summary = "Delete Selected Restaurant",
            description = "Delete all restaurant records in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete is successful"),
            @ApiResponse(responseCode = "500", description = "Error deleting selected restaurant from database.")
    })
	@DeleteMapping("/deleteSelectedRestaurant")
	public ResponseEntity<?> deleteSelectedRestaurant(@RequestParam(name="name") String name){
		try {
			List<Restaurant> result = service.deleteSelectedRestaurant(new Restaurant(name));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>("Error Deleting Selected Restaurant. Please try again.", HttpStatus.OK);
		}
	}
	
	
	
}
