package com.fooddelivery.servlet;

import java.io.IOException;
import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

import com.fooddelivery.model.User;
import com.fooddelivery.util.DBConnection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection con = DBConnection.getDBConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=?")) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String storedPassword = rs.getString("password");
                boolean isValid = false;

                // 🔐 handle both bcrypt + old plain passwords
                if (storedPassword.startsWith("$2")) {
                    isValid = BCrypt.checkpw(password, storedPassword);
                } else {
                    isValid = password.equals(storedPassword);
                }

                if (isValid) {

                    User user = new User();
                    user.setUserId(rs.getString("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));

                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    // ✅ success redirect
                    response.sendRedirect("restaurants");

                } else {
                    request.setAttribute("error", "Invalid credentials ❌");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("error", "User not found ❌");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}