package com.fooddelivery.dao;

import java.util.List;

import com.fooddelivery.model.Restaurant;

public interface RestaurantDAO {

	    void addRestaurant(Restaurant restaurant);

	    Restaurant getRestaurantById(String restaurantId);

	    List<Restaurant> getAllRestaurants();

	    void updateRestaurant(Restaurant restaurant);

	    void deleteRestaurant(String restaurantId);

	}
