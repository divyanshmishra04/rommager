package com.roomate.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/ExpenseServlet")

public class ExpenseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check what action user performed — either calculate total or split expense
        String action = request.getParameter("action");

        // Step 1️⃣: Calculate total from list of items
        if ("Calculate Total".equals(action)) {

            // JSP sends multiple 'price' parameters (one for each item)
            String[] prices = request.getParameterValues("price");

            double total = 0;

            if (prices != null) {
                for (String p : prices) {
                    if (p != null && !p.isEmpty()) {
                        total += Double.parseDouble(p);
                    }
                }
            }

            // Attach the total as an attribute for JSP
            request.setAttribute("total", total);

            // Forward back to same JSP to display total and ask for roommate count
            RequestDispatcher rd = request.getRequestDispatcher("Expense.jsp");
            rd.forward(request, response);
        }

        // Step 2️⃣: Split the total among roommates
        else if ("Split Expense".equals(action)) {

            double total = Double.parseDouble(request.getParameter("total"));
            int roommates = Integer.parseInt(request.getParameter("roommates"));

            double perPerson = total / roommates;

            // Attach both values back to JSP
            request.setAttribute("total", total);
            request.setAttribute("perPerson", perPerson);

            // Forward back to JSP to display the per-person share
            RequestDispatcher rd = request.getRequestDispatcher("Expense.jsp");
            rd.forward(request, response);
        }
    }
}
