package com.fooddelivery.servlet;

import java.io.IOException;
import java.util.List;

import com.fooddelivery.daoimpl.MenuDAOImpl;
import com.fooddelivery.model.Menu;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class MenuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String restaurantId = request.getParameter("restaurantId");

	    // 🧠 CREATE / GET SESSION
	    HttpSession session = request.getSession();

	    // 🔥 STORE restaurantId
	    session.setAttribute("restaurantId", restaurantId);

	    MenuDAOImpl dao = new MenuDAOImpl();
	    List<Menu> list = dao.getMenuByRestaurant(restaurantId);

	    request.setAttribute("menus", list);

	    RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
	    rd.forward(request, response);
	}
}