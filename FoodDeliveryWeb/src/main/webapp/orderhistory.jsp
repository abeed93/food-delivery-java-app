<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.fooddelivery.model.*, com.fooddelivery.daoimpl.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order History</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f5f6fa;
        margin: 0;
        padding: 20px;
    }

    h2 {
        text-align: center;
        margin-bottom: 30px;
    }
	
    .order-card {
        background: white;
        border-radius: 12px;
        padding: 15px 20px;
        margin: 15px auto;
        max-width: 600px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }

    .order-header {
        display: flex;
        justify-content: space-between;
        font-weight: bold;
        margin-bottom: 10px;
    }

    .status {
        color: green;
    }

    .items {
        margin-top: 10px;
        padding-left: 10px;
    }

    .item {
        display: flex;
        justify-content: space-between;
        padding: 5px 0;
        border-bottom: 1px dashed #ddd;
    }

    .item:last-child {
        border-bottom: none;
    }

    .total {
        text-align: right;
        font-weight: bold;
        margin-top: 10px;
    }

    .btn {
        display: inline-block;
        margin-top: 10px;
        padding: 8px 14px;
        background: #ff4d4d;
        color: white;
        text-decoration: none;
        border-radius: 6px;
    }

    .btn:hover {
        background: #e60000;
    }

    .empty {
        text-align: center;
        font-size: 18px;
        color: gray;
    }
</style>

</head>
<body>

<h2>📜 Order History</h2>

<%
List<Order> orders = (List<Order>) request.getAttribute("orders");

if(orders != null && !orders.isEmpty()) {

    OrderDAOImpl orderDAO = new OrderDAOImpl();
    MenuDAOImpl menuDAO = new MenuDAOImpl();

    for(Order o : orders) {
%>

<div class="order-card">

    <div class="order-header">
        <h3 style="color:blue;">🆔 Order: <%= o.getOrderId().substring(0,8) %></h3>
        <span class="status" style="color:green;">✔<%= o.getStatus() %></span>
    </div>

    <div>💳 Payment: <%= o.getPaymentMode() %></div>
    <div>🕒 <%= new java.text.SimpleDateFormat("dd MMM yyyy, hh:mm a")
        .format(o.getOrderTime()) %></div>

    <div class="items">
        <%
        List<CartItem> items = orderDAO.getItemsByOrderId(o.getOrderId());

        for(CartItem item : items) {
            Menu m = menuDAO.getMenuById(item.getMenuId());
            double itemTotal = m.getPrice() * item.getQuantity();
        %>

        <div class="item">
            <span>🍽️ <%= m.getItemName() %> (x<%= item.getQuantity() %>)</span>
            <span>₹<%= String.format("%.2f", itemTotal) %></span>
        </div>

        <%
        }
        
        %>
    </div>

    <div class="total">
        Total: ₹<%= String.format("%.2f", o.getTotalAmount()) %>
    </div>

</div>

<%
    }

} else {
%>

<p class="empty">No orders found 😢</p>

<%
}
%>

</body>
</html>