<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.fooddelivery.model.Menu" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Menu</title>

<style>
body {
    font-family: Arial;
    background: #f5f5f5;
    margin: 0;
}

/* Container */
.container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

/* Card */
.card {
    width: 230px;
    margin: 15px;
    border-radius: 12px;
    overflow: hidden;
    background: white;
    box-shadow: 0 2px 10px rgba(0,0,0,0.15);
    transition: all 0.3s ease;
}

.card:hover {
    transform: translateY(-8px);
    box-shadow: 0 6px 20px rgba(0,0,0,0.25);
}

/* Image */
.card img {
    width: 100%;
    height: 150px;
    object-fit: cover;
    transition: transform 0.4s ease;
}

.card:hover img {
    transform: scale(1.08);
}

/* Content */
.card-body {
    padding: 12px;
    text-align: center;
}

.price {
    color: green;
    font-weight: bold;
}

/* Button */
.btn {
    background: #ff4d4d;
    color: white;
    border: none;
    padding: 8px 12px;
    border-radius: 6px;
    cursor: pointer;
    transition: 0.2s;
}

.btn:hover {
    background: #e60000;
}

.btn:active {
    transform: scale(0.95);
}
</style>

</head>

<body>

<h2 style="text-align:center; padding:20px;">🍜 Explore Delicious Food</h2>
<div style="
    background:#ff4d4d;
    color:white;
    padding:12px 20px;
    display:flex;
    justify-content:space-between;
    align-items:center;
">

    <h2>🍽 FoodApp</h2>

    <div>
        <a href="restaurants" style="color:white; margin-right:15px;">Home</a>

        <a href="cart" style="color:white; margin-right:15px;">
            🛒 Cart (<span id="cartCount"><%= session.getAttribute("cartCount") == null ? 0 : session.getAttribute("cartCount") %></span>)
        </a>

        <a href="orders" style="color:white;">📜 Orders</a>
    </div>

</div>
<div class="container">

<%
List<Menu> list = (List<Menu>) request.getAttribute("menus");

if(list != null && !list.isEmpty()) {
    for(Menu m : list) {
%>

    <div class="card">

        <img src="<%= request.getContextPath() %>/<%= m.getImageUrl() %>">

        <div class="card-body">

            <h3><%= m.getItemName() %></h3>

            <p class="price">
                ₹<%= String.format("%.2f", m.getPrice()) %>
            </p>

           <a href="<%= request.getContextPath() %>/cart?menuId=<%= m.getMenuId() %>" onclick="showPopup()">
                <button class="btn">➕ Add to Cart</button>
            </a>

        </div>
    </div>

<%
    }
} else {
%>

    <h3>No Menu Available ❌</h3>

<%
}
%>

</div>

<div id="cartPopup" style="
    position:fixed;
    bottom:20px;
    right:20px;
    background:#28a745;
    color:white;
    padding:12px 20px;
    border-radius:8px;
    display:none;
    box-shadow:0 2px 10px rgba(0,0,0,0.2);
">
    🛒 Item added to cart!
</div>

<script>
function showPopup() {
    let popup = document.getElementById("cartPopup");
    popup.style.display = "block";

    setTimeout(() => {
        popup.style.display = "none";
    }, 2000);
}
</script>

</body>
</html>