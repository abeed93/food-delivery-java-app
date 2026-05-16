package com.fooddelivery.servlet;

import java.io.IOException;
import java.util.UUID;

import com.fooddelivery.daoimpl.OrderDAOImpl;
import com.fooddelivery.model.Cart;
import com.fooddelivery.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        User user = (User) session.getAttribute("user");

        if(user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userId = user.getUserId();
        
        if(cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        String orderId = UUID.randomUUID().toString();

        
        OrderDAOImpl dao = new OrderDAOImpl();
        
        String paymentMode = request.getParameter("payment");

        String restaurantId = (String) session.getAttribute("restaurantId");
        dao.saveOrder(orderId, cart, paymentMode, restaurantId, userId);
        
        request.setAttribute("orderedItems", cart.getItems());
        
        session.removeAttribute("cart"); // 🧹 clear cart
        session.removeAttribute("restaurantId");

        session.setAttribute("cart", new Cart());
        
        request.setAttribute("orderId", orderId);
        RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
        rd.forward(request, response);
        System.out.println("Checkout hit!");
        System.out.println("Checkout user: " + user.getUserId());
        
    }
}