<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.fooddelivery.model.Restaurant" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurants</title>

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
    width: 260px;
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
    height: 160px;
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

.btn {
    background: #ff4d4d;
    color: white;
    border: none;
    padding: 8px 12px;
    border-radius: 6px;
    cursor: pointer;
}

.btn:hover {
    background: #e60000;
}
</style>

</head>

<body>

<h2 style="text-align:center; padding:20px;">🍽️ Explore Restaurants</h2>
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
<div style="text-align:center; margin-bottom:20px;">
    <input 
        type="text" 
        id="searchInput" 
        placeholder="🔍 Search restaurants..."
        style="
            width:300px;
            padding:10px;
            border-radius:20px;
            border:1px solid #ccc;
            outline:none;
        "
    >
</div>

<div class="container">

<%
List<Restaurant> list = (List<Restaurant>) request.getAttribute("restaurants");

if(list != null && !list.isEmpty()) {
    for(Restaurant r : list) {
%>

    <div class="card">

        <img src="<%= request.getContextPath() %>/<%= r.getImagePath() %>">

        <div class="card-body">

            <h3><%= r.getName() %></h3>

            <p>🍴 <%= r.getCuisineType() %></p>

            <p>⭐ <%= r.getRating() %> | ⏱ <%= r.getDeliveryTime() %> mins</p>

            <a href="<%= request.getContextPath() %>/menu?restaurantId=<%= r.getRestaurantId() %>">
                <button class="btn">View Menu</button>
            </a>

        </div>
    </div>

<%
    }
} else {
%>

    <h3>No Restaurants Available ❌</h3>

<%
}
%>

</div>
<script>
function searchRestaurants() {
    let input = document.getElementById("searchInput").value.toLowerCase();
    let cards = document.getElementsByClassName("card");

    for (let i = 0; i < cards.length; i++) {
        let name = cards[i].innerText.toLowerCase();

        if (name.includes(input)) {
            cards[i].style.display = "block";
        } else {
            cards[i].style.display = "none";
        }
    }
}

// 🔥 Live search
document.getElementById("searchInput").addEventListener("keyup", searchRestaurants);
</script>
</body>
</html>