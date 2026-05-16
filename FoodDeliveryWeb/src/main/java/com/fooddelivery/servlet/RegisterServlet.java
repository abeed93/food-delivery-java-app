package com.fooddelivery.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.fooddelivery.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        String userId = UUID.randomUUID().toString();

        try (Connection con = DBConnection.getDBConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO users (user_id, name, email, password, address, role) VALUES (?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, userId);
            ps.setString(2, name);
            ps.setString(3, email);

            // 🔐 bcrypt
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            ps.setString(4, hashedPassword);

            ps.setString(5, address);
            ps.setString(6, "user");

            ps.executeUpdate();

            // ✅ success message via session
            request.getSession().setAttribute("msg", "Registered successfully 🎉 Please login.");

            // ✅ PRG pattern (no white screen)
            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("error", "Registration failed ❌");
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }
    }
}