package com.fooddelivery.model;

public class CartItem {
	
	 private String menuId;
	 private int quantity;
	 
	 public CartItem(String menuId, int quantity) {
		super();
		this.menuId = menuId;
		this.quantity = quantity;
	 }
	 public CartItem() {
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
	 
	 
}
