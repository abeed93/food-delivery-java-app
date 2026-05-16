package com.fooddelivery.dao;

import java.util.List;

import com.fooddelivery.model.Cart;
import com.fooddelivery.model.CartItem;
import com.fooddelivery.model.Order;

public interface OrderDAO {

	    void createOrder(Order order);

	    Order getOrderById(String orderId);

	    List<Order> getOrdersByUser(String userId);

	    List<Order> getAllOrders();

	    void updateOrderStatus(String orderId, String status);

	    void deleteOrder(String orderId);
	    
	    public void saveOrder(String orderId, Cart cart, String paymentMode, String restaurantId, String userId);
	    
	    public List<CartItem> getItemsByOrderId(String orderId);



	}
