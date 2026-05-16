package com.fooddelivery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fooddelivery.dao.RestaurantDAO;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

	private static final String INSERT = "INSERT INTO restaurant VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String GET_BY_ID = "SELECT * FROM restaurant WHERE restaurant_id = ?";

	private static final String GET_ALL = "SELECT * FROM restaurant";

	private static final String UPDATE = "UPDATE restaurant SET name=?, cuisine_type=?, delivery_time=?,"
			+ " address=?, admin_user_id=?, rating=?, is_active=?, image_path=? WHERE restaurant_id=?";

	private static final String DELETE = "DELETE FROM restaurant WHERE restaurant_id=?";

	@Override
	public void addRestaurant(Restaurant restaurant) {

		try (Connection connection = DBConnection.getDBConnection()) {

			PreparedStatement ps = connection.prepareStatement(INSERT);

			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, restaurant.getName());
			ps.setString(3, restaurant.getCuisineType());
			ps.setInt(4, restaurant.getDeliveryTime());
			ps.setString(5, restaurant.getAddress());
			ps.setString(6, restaurant.getAdminUserId());
			ps.setDouble(7, restaurant.getRating());
			ps.setBoolean(8, restaurant.isActive());
			ps.setString(9, restaurant.getImagePath());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Restaurant getRestaurantById(String restaurantId) {

	    Restaurant restaurant = null;

	    try(Connection connection = DBConnection.getDBConnection();
	        PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {

	        ps.setString(1, restaurantId);

	        var rs = ps.executeQuery();

	        if(rs.next()) {

	            restaurant = new Restaurant(
	                rs.getString("restaurant_id"),
	                rs.getString("name"),
	                rs.getString("cuisine_type"),
	                rs.getInt("delivery_time"),
	                rs.getString("address"),
	                rs.getString("admin_user_id"),
	                rs.getDouble("rating"),
	                rs.getBoolean("is_active"),
	                rs.getString("image_path")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return restaurant;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {

	    List<Restaurant> restaurants = new ArrayList<>();

	    try(Connection connection = DBConnection.getDBConnection();
	        PreparedStatement ps = connection.prepareStatement(GET_ALL)) {

	        var rs = ps.executeQuery();

	        while(rs.next()) {

	            Restaurant restaurant = new Restaurant(
	                rs.getString("restaurant_id"),
	                rs.getString("name"),
	                rs.getString("cuisine_type"),
	                rs.getInt("delivery_time"),
	                rs.getString("address"),
	                rs.getString("admin_user_id"),
	                rs.getDouble("rating"),
	                rs.getBoolean("is_active"),
	                rs.getString("image_path")
	            );

	            restaurants.add(restaurant);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return restaurants;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {

	    try(Connection connection = DBConnection.getDBConnection();
	        PreparedStatement ps = connection.prepareStatement(UPDATE)) {

	        ps.setString(1, restaurant.getName());
	        ps.setString(2, restaurant.getCuisineType());
	        ps.setInt(3, restaurant.getDeliveryTime());
	        ps.setString(4, restaurant.getAddress());
	        ps.setString(5, restaurant.getAdminUserId());
	        ps.setDouble(6, restaurant.getRating());
	        ps.setBoolean(7, restaurant.isActive());
	        ps.setString(8, restaurant.getImagePath());
	        ps.setString(9, restaurant.getRestaurantId());

	        ps.executeUpdate();

	        System.out.println("Restaurant updated successfully");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteRestaurant(String restaurantId) {

	    try(Connection connection = DBConnection.getDBConnection();
	        PreparedStatement ps = connection.prepareStatement(DELETE)) {

	        ps.setString(1, restaurantId);

	        ps.executeUpdate();

	        System.out.println("Restaurant deleted successfully");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
