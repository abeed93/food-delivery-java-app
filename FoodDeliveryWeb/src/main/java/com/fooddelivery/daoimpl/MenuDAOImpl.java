package com.fooddelivery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fooddelivery.dao.MenuDAO;
import com.fooddelivery.model.Menu;
import com.fooddelivery.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

	    private static final String INSERT =
	    "INSERT INTO menu VALUES (?,?,?,?,?,?,?)" ;

	    private static final String GET_BY_ID =
	    "SELECT * FROM menu WHERE menu_id = ?";

	    private static final String GET_BY_RESTAURANT =
	    "SELECT * FROM menu WHERE restaurant_id = ?";

	    private static final String GET_ALL =
	    "SELECT * FROM menu";

	    private static final String UPDATE =
	    "UPDATE menu SET item_name=?, description=?, price=?, is_available=?, image_url=? WHERE menu_id=?";

	    private static final String DELETE =
	    "DELETE FROM menu WHERE menu_id=?";

	    @Override
	    public void addMenuItem(Menu menu) {

	        try(Connection connection = DBConnection.getDBConnection();
	            PreparedStatement ps = connection.prepareStatement(INSERT)) {

	            ps.setString(1, UUID.randomUUID().toString());
	            ps.setString(2, menu.getRestaurantId());
	            ps.setString(3, menu.getItemName());
	            ps.setString(4, menu.getDescription());
	            ps.setDouble(5, menu.getPrice());
	            ps.setBoolean(6, menu.isAvailable());
	            ps.setString(7, menu.getImageUrl());

	            ps.executeUpdate();

	            System.out.println("Menu item added");

	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public Menu getMenuById(String menuId) {

	        Menu menu = null;

	        try(Connection connection = DBConnection.getDBConnection();
	            PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {

	            ps.setString(1, menuId);

	            ResultSet rs = ps.executeQuery();

	            if(rs.next()) {

	                menu = new Menu(
	                    rs.getString("menu_id"),
	                    rs.getString("restaurant_id"),
	                    rs.getString("item_name"),
	                    rs.getString("description"),
	                    rs.getDouble("price"),
	                    rs.getBoolean("is_available"),
	                    rs.getString("image_url")
	                );
	            }

	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	        return menu;
	    }

	    @Override
	    public List<Menu> getMenuByRestaurant(String restaurantId) {

	        List<Menu> menuList = new ArrayList<>();

	        try(Connection connection = DBConnection.getDBConnection();
	            PreparedStatement ps = connection.prepareStatement(GET_BY_RESTAURANT)) {

	            ps.setString(1, restaurantId);

	            ResultSet rs = ps.executeQuery();

	            while(rs.next()) {

	                Menu menu = new Menu(
	                    rs.getString("menu_id"),
	                    rs.getString("restaurant_id"),
	                    rs.getString("item_name"),
	                    rs.getString("description"),
	                    rs.getDouble("price"),
	                    rs.getBoolean("is_available"),
	                    rs.getString("image_url")
	                );

	                menuList.add(menu);
	            }

	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	        return menuList;
	    }

	    @Override
	    public List<Menu> getAllMenuItems() {

	        List<Menu> menuList = new ArrayList<>();

	        try(Connection connection = DBConnection.getDBConnection();
	            PreparedStatement ps = connection.prepareStatement(GET_ALL);
	            ResultSet rs = ps.executeQuery()) {

	            while(rs.next()) {

	                Menu menu = new Menu(
	                    rs.getString("menu_id"),
	                    rs.getString("restaurant_id"),
	                    rs.getString("item_name"),
	                    rs.getString("description"),
	                    rs.getDouble("price"),
	                    rs.getBoolean("is_available"),
	                    rs.getString("image_url")
	                );

	                menuList.add(menu);
	            }

	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	        return menuList;
	    }

	    @Override
	    public void updateMenuItem(Menu menu) {

	        try(Connection connection = DBConnection.getDBConnection();
	            PreparedStatement ps = connection.prepareStatement(UPDATE)) {

	            ps.setString(1, menu.getItemName());
	            ps.setString(2, menu.getDescription());
	            ps.setDouble(3, menu.getPrice());
	            ps.setBoolean(4, menu.isAvailable());
	            ps.setString(5, menu.getMenuId());

	            ps.executeUpdate();

	            System.out.println("Menu updated");

	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteMenuItem(String menuId) {

	        try(Connection connection = DBConnection.getDBConnection();
	            PreparedStatement ps = connection.prepareStatement(DELETE)) {

	            ps.setString(1, menuId);

	            ps.executeUpdate();

	            System.out.println("Menu item deleted");

	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

