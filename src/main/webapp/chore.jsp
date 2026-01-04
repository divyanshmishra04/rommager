<%@ page import="java.util.*, com.roomate.model.Chore" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chore Management</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f7f9fc; }
        table { width: 70%; border-collapse: collapse; margin: 20px auto; background: #fff; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: center; }
        th { background-color: #007bff; color: white; }
        input[type=submit] { padding: 10px 20px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
        input[type=submit]:hover { background: #0056b3; }
        .center { text-align: center; margin-top: 20px; }
    </style>
</head>
<body>

<h2 class="center"> Chore Management</h2>

<form action="ChoreServlet" method="post">
    <table>
        <tr>
            <th>Chore</th>
            <th>Last Done By</th>
            <th>Next Person</th>
            <th>Completed</th>
        </tr>
        <%
            List<Chore> chores = (List<Chore>) request.getAttribute("chores");
            if (chores != null && !chores.isEmpty()) {
                for (Chore c : chores) {
        %>
        <tr>
            <td><%= c.getChoreName() %></td>
            <td><%= c.getLastDoneBy() %></td>
            <td><%= c.getNextPerson() %></td>
            <td>
                <input type="checkbox" name="doneChores" value="<%= c.getChoreId() %>">
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="4">No chores found in the database.</td></tr>
        <% } %>
    </table>

    <div class="center">
        <input type="submit" value="Update">
    </div>
</form>

<script>

    const socket = new WebSocket("ws://localhost:8080/Roomager/notifications");

    socket.onopen = function() {
        console.log("WebSocket connection established.");
    };

    socket.onmessage = function(event) {

        const message = event.data;
        alert("Notification: " + message);
    };

    socket.onclose = function() {
        console.log(" WebSocket connection closed.");
    };

    socket.onerror = function(error) {
        console.error(" WebSocket error:", error);
    };
</script>


<div class="center">
    <form action="dashboard.jsp">
        <input type="submit" value="Back to Dashboard">
    </form>
</div>

</body>
</html>
