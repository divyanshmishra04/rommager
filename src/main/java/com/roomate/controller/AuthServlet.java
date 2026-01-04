package com.roomate.controller;

import com.roomate.dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Step 1: Get username and password entered by the user
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValid = false;

        try {
            // Step 2: Pass the variables
            isValid = UserDao.isValid(name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Step 3: If valid, create a session and go to dashboard
        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("username", name); // store the username in session
            response.sendRedirect("dashboard.jsp");
        } else {
            // Step 4: If invalid, redirect back to login with an error message
            response.sendRedirect("index.jsp?error=Invalid+username+or+password");
        }
    }
}
