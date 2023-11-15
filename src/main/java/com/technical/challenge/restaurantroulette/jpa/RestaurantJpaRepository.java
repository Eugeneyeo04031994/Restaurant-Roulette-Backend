package com.technical.challenge.restaurantroulette.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technical.challenge.restaurantroulette.entity.Restaurant;

public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Long>{

	Optional<Restaurant> findByName(String name);
	Optional<Restaurant> findFirstByName(String name);
	
}
