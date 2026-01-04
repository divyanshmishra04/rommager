package com.roomate.controller;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

// This servlet handles logout functionality — it destroys the session and redirects to index.jsp
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the current session, if it exists
        HttpSession session = request.getSession(false);

        // If a session exists, invalidate (destroy) it
        if (session != null) {
            session.invalidate();
        }

        // Redirect user back to login page
        response.sendRedirect("logout.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
