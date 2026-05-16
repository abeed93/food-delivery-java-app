<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FoodApp</title>

<style>
body {
    margin:0;
    font-family: Arial;
}

/* 🌈 Navbar */
.navbar {
    position: sticky;
    top:0;
    backdrop-filter: blur(10px);
    background: rgba(255,77,77,0.85);
    padding:15px 30px;
    display:flex;
    justify-content:space-between;
}

.navbar a {
    color:white;
    margin-left:15px;
    text-decoration:none;
}

.search-bar {
    width: 300px;
    padding: 12px;
    border-radius: 25px;
    border: none;
    margin-top: 15px;
    outline: none;
    font-size: 14px;
}

.hero-btn {
    margin-top: 20px;
    padding: 12px 20px;
    background:#ff4d4d;
    border:none;
    color:white;
    border-radius:5px;
    cursor:pointer;
}

/* 🎯 Hero Section */
.hero {
    height:80vh;
    background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)),
                url('images/hero.jpg');
    background-size:cover;
    background-position:center;
    display:flex;
    justify-content:center;
    align-items:center;
    color:white;
    text-align:center;
}

.hero h1 {
    font-size:40px;
}

.hero button {
    margin-top:20px;
    padding:12px 20px;
    background:#ff4d4d;
    border:none;
    color:white;
    border-radius:5px;
    cursor:pointer;
}

/* 🍽 Categories */
.categories {
    display:flex;
    justify-content:center;
    margin:40px 0;
}

.cat {
    margin:15px;
    text-align:center;
}

.cat img {
    width:120px;
    height:120px;
    border-radius:50%;
    object-fit:cover;
    transition:0.3s;
}

.cat img:hover {
    transform:scale(1.1);
}

/* 📦 Footer */
.footer {
    background:#222;
    color:white;
    text-align:center;
    padding:15px;
}
</style>

</head>

<body>

<!-- 🧭 NAVBAR -->
<div class="navbar">
    <h2>🍽 FoodApp</h2>
    <div>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
    </div>
</div>

<!-- 🎯 HERO -->
<div class="hero">
    <div>
        <h1>Craving Something Delicious? 🍔</h1>

        <input 
            type="text" 
            id="searchBox"
            placeholder="🔍 Search food or restaurants..."
            class="search-bar"
        >

        <br>

        <a href="restaurants">
            <button class="hero-btn">Explore Now 🍽️</button>
        </a>
    </div>
</div>

<!-- 🍕 CATEGORIES -->

<h2 style="text-align:center;">Popular Categories</h2>

<div class="categories">

    <div class="cat">
        <img src="images/biryani1.jpg">
        <p>Biryani</p>
    </div>

    <div class="cat">
        <img src="images/pizza1.jpg">
        <p>Pizza</p>
    </div>

    <div class="cat">
        <img src="images/burger1.jpg">
        <p>Burgers</p>
    </div>

    <div class="cat">
        <img src="images/paneercurry1.jpg">
        <p>Indian</p>
    </div>

</div>

<h2 style="text-align:center;">🔥 Top Restaurants</h2>

<div class="container">

<%
List<com.fooddelivery.model.Restaurant> list =
    (List<com.fooddelivery.model.Restaurant>) request.getAttribute("restaurants");

if(list != null) {
    for(int i=0; i<Math.min(4, list.size()); i++) {
        com.fooddelivery.model.Restaurant r = list.get(i);
%>

<div class="card">
    <img src="<%= request.getContextPath() %>/<%= r.getImagePath() %>">
    <div class="card-body">
        <h3><%= r.getName() %></h3>
        <p>⭐ <%= r.getRating() %></p>
    </div>
</div>

<%
    }
}
%>

</div>

<!-- ⭐ CTA -->
<div style="text-align:center; margin:40px;">
    <h2>Hungry? Let’s fix that 🍽️</h2>
    <a href="restaurants">
        <button style="padding:10px 20px; background:#ff4d4d; color:white; border:none;">
            Order Now
        </button>
    </a>
</div>

<!-- 📦 FOOTER -->
<div class="footer">
    <p>© 2026 FoodApp | Built by You 💼🔥</p>
</div>

</body>
</html>