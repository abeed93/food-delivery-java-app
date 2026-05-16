package com.fooddelivery.model;

public class OrderItems {
	private String orderItemsId;
	private String orderId;
	private String menuId;
	private int quantity;
	private double totalPrice;
	public OrderItems(String orderItemsId, String orderId, String menuId, int quantity, double totalPrice) {
		super();
		this.orderItemsId = orderItemsId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public String getOrderItemsId() {
		return orderItemsId;
	}
	public void setOrderItemsId(String orderItemsId) {
		this.orderItemsId = orderItemsId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	
}
