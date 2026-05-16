package com.fooddelivery.dao;

import java.util.List;

import com.fooddelivery.model.Menu;

public interface MenuDAO {

	    void addMenuItem(Menu menu);

	    Menu getMenuById(String menuId);

	    List<Menu> getMenuByRestaurant(String restaurantId);

	    List<Menu> getAllMenuItems();

	    void updateMenuItem(Menu menu);

	    void deleteMenuItem(String menuId);
	}

