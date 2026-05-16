package com.fooddelivery.dao;

import java.util.List;

import com.fooddelivery.model.OrderItems;

public interface OrderItemsDAO {

	    void addOrderItem(OrderItems orderItem);

	    List<OrderItems> getItemsByOrder(String orderId);

	    void updateOrderItem(OrderItems orderItem);

	    void deleteOrderItem(String orderItemId);
	}

