package com.roomate.controller;

import com.roomate.dao.ChoreDao;
import com.roomate.model.Chore;
import com.roomate.websocket.NotificationEndpoint;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ChoreServlet")
public class ChoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch all chores from the database
        List<Chore> chores = ChoreDao.getAllChores();

        // Attach chores to the request
        request.setAttribute("chores", chores);

        // Forward to chore.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("chore.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //  Get which chores were marked done
        String[] doneChores = request.getParameterValues("doneChores");

        if (doneChores != null) {
            for (String choreIdStr : doneChores) {
                int choreId = Integer.parseInt(choreIdStr);

                // Fetch current chore
                Chore currentChore = ChoreDao.getChoreById(choreId);

                if (currentChore != null) {
                    String currentPerson = currentChore.getNextPerson(); // The one doing it now
                    String nextPerson = ChoreDao.getNextPersonAfter(currentPerson); // Rotate to next roommate

                    //  Update in database
                    ChoreDao.markChoreDone(choreId, currentPerson, nextPerson);

                    // 🔔 Send real-time notification
                    String message = "hi " + currentPerson + " completed the chore '"
                            + currentChore.getChoreName() + "'. Now it's "
                            + nextPerson + "'s turn!";
                    NotificationEndpoint.sendNotification(message);

                }
            }
        }

        // Refresh page
        response.sendRedirect("ChoreServlet");
    }
}
