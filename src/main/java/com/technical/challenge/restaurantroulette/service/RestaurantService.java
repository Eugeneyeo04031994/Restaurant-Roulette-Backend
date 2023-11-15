package com.technical.challenge.restaurantroulette.service;

import java.util.List;
import com.technical.challenge.restaurantroulette.entity.Restaurant;

public interface RestaurantService {

	boolean createRestaurant(Restaurant restaurant);
	Restaurant getRandomRestaurant();
	List<Restaurant> getAllRestaurantRecord();
	List<Restaurant> deleteSelectedRestaurant(Restaurant restaurant);
	List<Restaurant> deleteAllRestaurantRecord();
}
