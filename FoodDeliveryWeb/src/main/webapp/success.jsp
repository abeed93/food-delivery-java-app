<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>

<style>
body {
    margin:0;
    font-family: Arial;
    background: linear-gradient(135deg, #28a745, #5cd65c);
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
}

/* Card */
.card {
    background:white;
    padding:30px;
    border-radius:12px;
    width:400px;
    text-align:center;
    box-shadow:0 4px 15px rgba(0,0,0,0.3);
}

/* Buttons */
.btn {
    display:inline-block;
    margin:10px;
    padding:10px 15px;
    border:none;
    border-radius:6px;
    text-decoration:none;
    color:white;
}

.primary {
    background:#ff4d4d;
}

.secondary {
    background:#333;
}
</style>

</head>

<body>

<div class="card">

<%@ page import="java.util.*" %>
<%@ page import="com.fooddelivery.model.*" %>
<%@ page import="com.fooddelivery.daoimpl.MenuDAOImpl" %>

<h2>🎉 Order Placed Successfully!</h2>

<p>Thank you for ordering 🍽️</p>

<h3>🧾 Your Order:</h3>

<%
Collection<CartItem> items = (Collection<CartItem>) request.getAttribute("orderedItems");
MenuDAOImpl dao = new MenuDAOImpl();

double total = 0;

if(items != null) {
    for(CartItem item : items) {

        Menu m = dao.getMenuById(item.getMenuId());
        double itemTotal = m.getPrice() * item.getQuantity();
        total += itemTotal;
%>

<p>🍽 <%= m.getItemName() %> (x<%= item.getQuantity() %>)</p>
<p>₹ <%= itemTotal %></p>
<hr>

<%
    }
}
%>

<h3>💰 Total: ₹<%= total %></h3>

<p>⏱ Estimated Delivery: 30 mins</p>

<br>

<a href="restaurants">🍽️ Order More</a>
<a href="orders">📜 View Orders</a>
</div>

</body>
</html>