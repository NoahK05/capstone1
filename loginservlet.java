package com.cloudstorage.controller;

import com.cloudstorage.dao.UserDAOImpl;
import com.cloudstorage.model.User;
import com.cloudstorage.util.PasswordHasher; // ✅ Added import

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.getUserByUsername(username);

        if (user != null) {
            String inputHash = PasswordHasher.hashPassword(password, user.getSalt());
            if (inputHash.equals(user.getPassword())) {
                // ✅ Login successful
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("dashboard.jsp");
                return;
            }
        }

        // ❌ Login failed
        request.setAttribute("error", "Invalid username or password");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
