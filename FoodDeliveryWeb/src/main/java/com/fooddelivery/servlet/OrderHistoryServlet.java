package com.fooddelivery.servlet;

import java.io.IOException;
import java.util.List;

import com.fooddelivery.daoimpl.OrderDAOImpl;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderHistoryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    HttpSession session = request.getSession();

	    User user = (User) session.getAttribute("user");

	    if(user == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }

	    OrderDAOImpl dao = new OrderDAOImpl();

	    // 🔥 FILTER BY USER
	    List<Order> orders = dao.getOrdersByUser(user.getUserId());

	    request.setAttribute("orders", orders);

	    RequestDispatcher rd = request.getRequestDispatcher("orderhistory.jsp");
	    rd.forward(request, response);
	    
	}
}