<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fooddelivery.model.Cart" %>
<%@ page import="com.fooddelivery.model.CartItem" %>
<%@ page import="java.util.*" %>

<%@ page import="com.fooddelivery.model.*" %>
<%@ page import="com.fooddelivery.daoimpl.MenuDAOImpl" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>

<style>
body {
    font-family: Arial;
    background: #f5f5f5;
    margin: 0;
}

/* Navbar */
.navbar {
    background:#ff4d4d;
    color:white;
    padding:12px 20px;
    display:flex;
    justify-content:space-between;
}

/* Container */
.container {
    display:flex;
    flex-wrap:wrap;
    justify-content:center;
}

/* Card */
.card {
    width:260px;
    background:white;
    margin:15px;
    border-radius:12px;
    overflow:hidden;
    box-shadow:0 2px 10px rgba(0,0,0,0.2);
    transition:0.3s;
}

.card:hover {
    transform:translateY(-6px);
}

/* Image */
.card img {
    width:100%;
    height:150px;
    object-fit:cover;
}

/* Body */
.card-body {
    padding:10px;
    text-align:center;
}

/* Buttons */
.btn {
    background:#ff4d4d;
    color:white;
    border:none;
    padding:5px 10px;
    border-radius:5px;
    cursor:pointer;
}

.qty a {
    margin:0 8px;
    text-decoration:none;
    font-size:18px;
}

.total-box {
    text-align:center;
    margin:20px;
    font-size:20px;
    font-weight:bold;
}

.checkout-btn {
    display:block;
    margin:20px auto;
    background:green;
    color:white;
    padding:10px 20px;
    border:none;
    border-radius:6px;
}
</style>

</head>

<body>

<div class="navbar">
    <h2>🍽 FoodApp</h2>
    <div>
        <a href="restaurants" style="color:white;">Home</a> |
        🛒 (<%= session.getAttribute("cartCount") == null ? 0 : session.getAttribute("cartCount") %>)
    </div>
</div>

<div class="container">

<%
Cart cart = (Cart) session.getAttribute("cart");

if(cart != null && !cart.getItems().isEmpty()) {

    MenuDAOImpl dao = new MenuDAOImpl();
    double grandTotal = 0;

    for(CartItem item : cart.getItems()) {

        Menu m = dao.getMenuById(item.getMenuId());
        double total = m.getPrice() * item.getQuantity();
        grandTotal += total;
%>

<div class="card">

    <img src="<%= request.getContextPath() %>/<%= m.getImageUrl() %>">

    <div class="card-body">

        <h3><%= m.getItemName() %></h3>

        <p>₹<%= m.getPrice() %></p>

        <div class="qty">
            <a href="update?menuId=<%= item.getMenuId() %>&action=decrease">➖</a>
            <b><%= item.getQuantity() %></b>
            <a href="update?menuId=<%= item.getMenuId() %>&action=increase">➕</a>
        </div>

        <p>Subtotal: ₹<%= total %></p>

        <a href="remove?menuId=<%= item.getMenuId() %>" class="btn">❌ Remove</a>

    </div>
</div>

<%
    }
%>

</div>

<div class="total-box">
    💰 Grand Total: ₹<%= grandTotal %>
</div>

<a href="checkout.jsp">
    <button class="checkout-btn">💳 Checkout</button>
</a>

<%
} else {
%>

<h3 style="text-align:center;">Cart is empty 😢</h3>

<%
}
%>

</body>
</html>