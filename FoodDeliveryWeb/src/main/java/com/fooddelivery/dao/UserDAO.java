package com.fooddelivery.dao;

import java.util.List;

import com.fooddelivery.model.User;

public interface UserDAO {

	void addUser(User user);
	
	User getUserById(String userId);

    User getUserByEmail(String email);
    
	void updateUser(User user);
	
	void deleteUser(String userId);
	
	List<User> getAllUsers();

}
