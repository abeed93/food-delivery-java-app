<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<style>
body {
    margin:0;
    font-family: Arial;
    background: linear-gradient(135deg, #ff4d4d, #ff9966);
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
}

/* 🧾 Card */
.card {
    background:white;
    padding:30px;
    border-radius:12px;
    width:320px;
    box-shadow:0 4px 15px rgba(0,0,0,0.3);
    text-align:center;
    animation: fadeIn 0.6s ease;
}

/* ✨ Animation */
@keyframes fadeIn {
    from {opacity:0; transform:translateY(20px);}
    to {opacity:1; transform:translateY(0);}
}

/* Inputs */
input {
    width:90%;
    padding:10px;
    margin:10px 0;
    border-radius:6px;
    border:1px solid #ccc;
    outline:none;
}

/* Button */
button {
    background:#ff4d4d;
    color:white;
    border:none;
    padding:10px;
    width:100%;
    border-radius:6px;
    cursor:pointer;
    font-size:15px;
}

button:hover {
    background:#e60000;
}

/* Link */
a {
    text-decoration:none;
    color:#ff4d4d;
    font-weight:bold;
}
</style>

</head>

<body>

<div class="card">

<h2>📝 Create Account</h2>

<form action="register" method="post">

    <input type="text" name="name" placeholder="Full Name" required><br>

    <input type="email" name="email" placeholder="Email" required><br>

    <input type="password" name="password" placeholder="Password" required><br>

    <input type="text" name="address" placeholder="Address" required><br>

    <button type="submit">Register 🚀</button>

</form>

<br>

<p>Already have an account?</p>
<a href="login.jsp">Login here 🔐</a>

<%
String error = (String) request.getAttribute("error");
if(error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
}
%>

</div>

</body>
</html>	