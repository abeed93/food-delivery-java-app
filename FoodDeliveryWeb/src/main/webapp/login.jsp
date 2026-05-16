<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<style>
body {
    margin:0;
    font-family:Arial;
    background: linear-gradient(135deg, #ff4d4d, #ff9966);
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
}

.card {
    background:white;
    padding:30px;
    border-radius:12px;
    width:300px;
    box-shadow:0 4px 15px rgba(0,0,0,0.3);
    text-align:center;
}

input {
    width:90%;
    padding:10px;
    margin:10px 0;
    border-radius:5px;
    border:1px solid #ccc;
}

button {
    background:#ff4d4d;
    color:white;
    border:none;
    padding:10px;
    width:100%;
    border-radius:5px;
    cursor:pointer;
}

button:hover {
    background:#e60000;
}
</style>

</head>

<body>

<div class="card">

<h2>🔐 Login</h2>

<form action="login" method="post">
    <input type="text" name="email" placeholder="Email"><br>
    <input type="password" name="password" placeholder="Password"><br>
    <button type="submit">Login</button>
</form>

<br>

<a href="register.jsp">Create Account</a>

<%
String msg = (String) session.getAttribute("msg");
if(msg != null) {
%>
<p style="color:green;"><%= msg %></p>
<%
session.removeAttribute("msg");
}
%>

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