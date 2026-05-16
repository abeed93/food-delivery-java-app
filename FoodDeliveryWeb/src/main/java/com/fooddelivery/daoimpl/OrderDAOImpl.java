package com.fooddelivery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fooddelivery.dao.OrderDAO;
import com.fooddelivery.model.Cart;
import com.fooddelivery.model.CartItem;
import com.fooddelivery.model.Menu;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderStatus;
import com.fooddelivery.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT =
    "INSERT INTO orders (order_id, user_id, restaurant_id, total_amount, status, payment_mode, order_time) VALUES (?,?,?,?,?,?,?)";

    private static final String GET_BY_ID =
    "SELECT * FROM orders WHERE order_id=?";

    private static final String GET_BY_USER =
    "SELECT * FROM orders WHERE user_id=?";

    private static final String GET_ALL =
    "SELECT * FROM orders ORDER BY order_date DESC";

    private static final String UPDATE_STATUS =
    "UPDATE orders SET status=? WHERE order_id=?";

    private static final String DELETE =
    "DELETE FROM orders WHERE order_id=?";

    @Override
    public void createOrder(Order order) {

        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT)) {

            String id = UUID.randomUUID().toString();
            order.setOrderId(id);

            ps.setString(1, id);
            ps.setString(2, order.getUserId());
            ps.setString(3, order.getRestaurantId());
            ps.setDouble(4, order.getTotalAmount());
            ps.setString(5, order.getStatus().name()); // ✅ ENUM → STRING
            ps.setString(6, order.getPaymentMode());
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

            ps.executeUpdate();

            System.out.println("Order created successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(String orderId) {

        Order order = null;

        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {

            ps.setString(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                order = new Order(
                    rs.getString("order_id"),
                    rs.getString("user_id"),
                    rs.getString("restaurant_id"),
                    rs.getDouble("total_amount"),
                    OrderStatus.valueOf(rs.getString("status")), // ✅ STRING → ENUM
                    rs.getString("payment_mode"),
                    rs.getTimestamp("order_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
   public List<Order> getOrdersByUser(String userId) {

    List<Order> list = new ArrayList<>();

    try(Connection con = DBConnection.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM orders WHERE user_id = ?"
        )) {

        ps.setString(1, userId);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Order o = new Order(
                rs.getString("order_id"),
                rs.getString("user_id"),
                rs.getString("restaurant_id"),
                rs.getDouble("total_amount"),
                OrderStatus.valueOf(rs.getString("status")),
                rs.getString("payment_mode"),
                rs.getTimestamp("order_date")
            );
            list.add(o);
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return list;
}

    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Order order = new Order(
                    rs.getString("order_id"),
                    rs.getString("user_id"),
                    rs.getString("restaurant_id"),
                    rs.getDouble("total_amount"),
                    OrderStatus.valueOf(rs.getString("status")),
                    rs.getString("payment_mode"),
                    rs.getTimestamp("order_date")
                );

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {

        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS)) {

            ps.setString(1, status.toUpperCase());
            ps.setString(2, orderId);

            ps.executeUpdate();

            System.out.println("Order status updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(String orderId) {

        try (Connection connection = DBConnection.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {

            ps.setString(1, orderId);

            ps.executeUpdate();

            System.out.println("Order deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void saveOrder(String orderId, Cart cart, String paymentMode, String restaurantId, String userId) {

        try(Connection con = DBConnection.getDBConnection()) {

            MenuDAOImpl menuDAO = new MenuDAOImpl();

            double totalAmount = 0;

            // calculate total
            for(CartItem item : cart.getItems()) {
                Menu m = menuDAO.getMenuById(item.getMenuId());
                totalAmount += m.getPrice() * item.getQuantity();
                System.out.println("Saving item: " + item.getMenuId() + " qty: " + item.getQuantity());
            }

            PreparedStatement ps1 = con.prepareStatement(
            	    "INSERT INTO orders (order_id, user_id, restaurant_id, total_amount, status, payment_mode, order_date) VALUES (?, ?, ?, ?, ?, ?, ?)"
            	);
            

            	ps1.setString(1, orderId);
            	ps1.setString(2, userId); 
            	ps1.setString(3, restaurantId);
            	ps1.setDouble(4, totalAmount);
            	ps1.setString(5, "PLACED");
            	ps1.setString(6, paymentMode);
            	ps1.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
            	
            	ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO order_item (order_item_id, order_id, menu_id, quantity, total_price) VALUES (?, ?, ?, ?, ?)"
            );

            for(CartItem item : cart.getItems()) {

                Menu m = menuDAO.getMenuById(item.getMenuId());

                String orderItemId = java.util.UUID.randomUUID().toString();
                double itemTotal = m.getPrice() * item.getQuantity();

                ps2.setString(1, orderItemId);
                ps2.setString(2, orderId);
                ps2.setString(3, item.getMenuId());
                ps2.setInt(4, item.getQuantity());
                ps2.setDouble(5, itemTotal);

                ps2.executeUpdate();
            }
            System.out.println(con.getCatalog());
            ps1.close();
            ps2.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Saving order...");
        
        System.out.println("Items count: " + cart.getItems().size());
        
    }

	@Override
	public List<CartItem> getItemsByOrderId(String orderId) {
		 {

		        List<CartItem> list = new ArrayList<>();

		        try(Connection con = DBConnection.getDBConnection();
		            PreparedStatement ps = con.prepareStatement(
		                "SELECT * FROM order_item WHERE order_id = ?"
		            )) {

		            ps.setString(1, orderId);

		            ResultSet rs = ps.executeQuery();

		            while(rs.next()) {

		                CartItem item = new CartItem(rs.getString("menu_id"),
		                	    rs.getInt("quantity"));

		                list.add(item);
		            }

		        } catch(Exception e) {
		            e.printStackTrace();
		        }

		        return list;
		    }
	}


    
    
    
    
    
}