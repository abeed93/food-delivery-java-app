<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>

<style>
body {
    margin:0;
    font-family: Arial;
    background: linear-gradient(135deg, #ff9966, #ff4d4d);
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
    width:350px;
    box-shadow:0 4px 15px rgba(0,0,0,0.3);
    text-align:center;
}

/* Payment options */
.option {
    text-align:left;
    margin:10px 0;
}

button {
    margin-top:20px;
    background:#ff4d4d;
    color:white;
    border:none;
    padding:10px;
    width:100%;
    border-radius:6px;
    cursor:pointer;
}

button:hover {
    background:#e60000;
}
</style>

</head>

<body>

<div class="card">

<h2>💳 Checkout</h2>

<form action="checkout" method="get">

    <h3>Select Payment Method</h3>

    <div class="option">
        <input type="radio" name="payment" value="COD" checked> 💵 Cash on Delivery
    </div>

    <div class="option">
        <input type="radio" name="payment" value="UPI"> 📱 UPI
    </div>

    <div class="option">
        <input type="radio" name="payment" value="CARD"> 💳 Card
    </div>

    <button type="submit">Place Order 🚀</button>

</form>

<br>

<a href="cart" style="text-decoration:none;">⬅ Back to Cart</a>

</div>

</body>
</html>