package com.technical.challenge.restaurantroulette.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.technical.challenge.restaurantroulette.entity.Restaurant;
import com.technical.challenge.restaurantroulette.jpa.RestaurantJpaRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantJpaRepository repository;

	@Override
	public boolean createRestaurant(Restaurant restaurant) {
		Optional<Restaurant> result = repository.findFirstByName(restaurant.getName());
		if(result.isPresent()) {
			return false;
		}
		else {
			repository.save(restaurant);
			return true;
		}
	}
	
	@Override
	public Restaurant getRandomRestaurant() {
		List<Restaurant> restaurantList = repository.findAll();
		Random random = new Random();
		int randomNumber = random.nextInt(restaurantList.size());
		return restaurantList.get(randomNumber);
	}
	
	@Override
	@Transactional
	public List<Restaurant> deleteAllRestaurantRecord() {	
		repository.deleteAll();
		return repository.findAll();
	}
	
	@Override
	public List<Restaurant> getAllRestaurantRecord(){
		return repository.findAll();
	}
	
	@Override
	@Transactional
	public List<Restaurant> deleteSelectedRestaurant(Restaurant restaurant) {
		repository.delete(restaurant);
		return repository.findAll();
	}
	
}
