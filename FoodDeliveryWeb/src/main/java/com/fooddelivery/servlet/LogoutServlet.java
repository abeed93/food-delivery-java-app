package com.fooddelivery.servlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        HttpSession session = request.getSession();

	        session.invalidate(); // 💥 destroy session

	        // 🔥 Send logout message
	        request.setAttribute("msg", "Logged out successfully 🚪");

	        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	        rd.forward(request, response);
	    }
}