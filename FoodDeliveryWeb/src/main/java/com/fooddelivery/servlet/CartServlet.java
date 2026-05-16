package com.fooddelivery.servlet;

import java.io.IOException;

import com.fooddelivery.model.Cart;
import com.fooddelivery.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")
public class CartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String menuId = request.getParameter("menuId");

	    HttpSession session = request.getSession();

	    Cart cart = (Cart) session.getAttribute("cart");

	    if(cart == null) {
	        cart = new Cart();
	        session.setAttribute("cart", cart);
	    }

	    // only add if coming from menu
	    if(menuId != null) {
	        cart.addItem(menuId);
	    }

	    // update badge
	    session.setAttribute("cartCount", cart.getTotalItems());

	    // 🔥 IMPORTANT: forward to JSP (NOT redirect)
	    request.getRequestDispatcher("cart.jsp").forward(request, response);
	    
	}
}