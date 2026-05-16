package com.fooddelivery.servlet;

import java.io.IOException;

import com.fooddelivery.model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

@SuppressWarnings("serial")
public class UpdateCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menuId = request.getParameter("menuId");
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if(cart != null && menuId != null && action != null) {

            // ✅ Single source of truth
            cart.updateQuantity(menuId, action);

            // 🔥 Update cart count badge
            session.setAttribute("cartCount", cart.getTotalItems());
        }

        response.sendRedirect("cart");
    }
}