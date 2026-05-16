	package com.fooddelivery.servlet;

	import java.io.IOException;
	import java.util.List;

import com.fooddelivery.daoimpl.RestaurantDAOImpl;
import com.fooddelivery.model.Restaurant;

import jakarta.servlet.*;
	import jakarta.servlet.http.*;

	@SuppressWarnings("serial")
	public class RestaurantServlet extends HttpServlet {
		
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        RestaurantDAOImpl dao = new RestaurantDAOImpl();

	        List<Restaurant> list = dao.getAllRestaurants();

	        // store data in request
	        request.setAttribute("restaurants", list);

	        // forward to JSP
	        RequestDispatcher rd = request.getRequestDispatcher("restaurant.jsp");
	        rd.forward(request, response);
	        System.out.println(list);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        doGet(request, response); // 🔥 handles POST → GET
	    }
	}

