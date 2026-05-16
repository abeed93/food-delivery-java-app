package com.fooddelivery.model;

import java.sql.Timestamp;

public class Order {

    private String orderId;
    private String userId;
    private String restaurantId;
    private double totalAmount;
    private OrderStatus status;
    private String paymentMode;
    private Timestamp orderDate;

    

    // Getters & Setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Timestamp getOrderTime() {
        return orderDate;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderDate = orderTime;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId +
                ", userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                ", paymentMode=" + paymentMode +
                ", orderDate=" + orderDate + "]";
    }
    public Order(String orderId, String userId, String restaurantId,
            double totalAmount, OrderStatus status,
            String paymentMode, Timestamp orderDate) {

   this.orderId = orderId;
   this.userId = userId;
   this.restaurantId = restaurantId;
   this.totalAmount = totalAmount;
   this.status = status;
   this.paymentMode = paymentMode;
   this.orderDate = orderDate;
}
}