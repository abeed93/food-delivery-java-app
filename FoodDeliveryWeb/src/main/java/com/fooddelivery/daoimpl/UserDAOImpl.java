package com.fooddelivery.daoimpl;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fooddelivery.dao.UserDAO;
import com.fooddelivery.model.User;
import com.fooddelivery.util.DBConnection;

public class UserDAOImpl implements UserDAO{

	private static final String INSERT = "INSERT into users( user_id, name, email, password, address, "
			+ "role, created_date, last_login_date, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	private static final String UPDATE = "UPDATE users set name = ?, email = ?, password = ?, address =  ?, "
			+ "role = ?, phone = ?";
	
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM USERS WHERE email = ?";
	
	private static final String GET_USER_BY_ID = "Select * FROM users WHERE user_id = ?";
	
	private static final String DELETE_USER_BY_EMAIL = "Delete from users WHERE email = ?";

	private static final String GET_ALL = "SELECT * FROM orders WHERE user_id = ?";

	@Override
	public void addUser(User user) {

		try(Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
			
			
			String id = UUID.randomUUID().toString();
			user.setUserId(id);
			
			preparedStatement.setString(1, user.getUserId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getEmail());

			String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			preparedStatement.setString(4, hashed);
				
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(9, user.getPhone());
			
			preparedStatement.executeUpdate();
			
			System.out.println("User Inserted Successfully");
			
			System.out.println(user.getRole());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void updateUser(User user) {
		try(Connection connection = DBConnection.getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
			
			
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setString(6, user.getPhone());
			
			int executeUpdate = preparedStatement.executeUpdate();
			
			System.out.println(executeUpdate + "User Updated");
			
			System.out.println(user.getRole());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(String email) {
		try {
			Connection connection = DBConnection.getDBConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_EMAIL);
			preparedStatement.setString(1, email);
			
			int executeUpdate = preparedStatement.executeUpdate();
			
			System.out.println(executeUpdate + " User Deleted" );
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> arrayList = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getDBConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User(
						resultSet.getString("user_id"),
						resultSet.getString("name"),
						resultSet.getString("email"),
						resultSet.getString("password"),
						resultSet.getString("address"),
						resultSet.getString("role"),
						resultSet.getTimestamp("created_date"),
						resultSet.getTimestamp("last_login_date"),
						resultSet.getString("phone")
						);
				arrayList.add(user);	
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrayList;
	}

	@Override
	public User getUserById(String userId) {
		User user = null;
		try {
			Connection connection = DBConnection.getDBConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				user = new User(
						resultSet.getString("user_id"),
						resultSet.getString("name"),
						resultSet.getString("email"),
						resultSet.getString("password"),
						resultSet.getString("address"),
						resultSet.getString("role"),
						resultSet.getTimestamp("created_date"),
						resultSet.getTimestamp("last_login_date"),
						resultSet.getString("phone")
						);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			Connection connection = DBConnection.getDBConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL);
			
			preparedStatement.setString(1, email);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				user = new User(
				resultSet.getString("user_id"),
				resultSet.getString("name"),
				resultSet.getString("email"),
				resultSet.getString("password"),
				resultSet.getString("address"),
				resultSet.getString("role"),
				resultSet.getTimestamp("created_date"),
				resultSet.getTimestamp("last_login_date"),
				resultSet.getString("phone")
				);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	

	
}
