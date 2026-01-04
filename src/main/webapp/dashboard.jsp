<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Roommate Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f6fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: #ffffff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 400px;
        }

        h2 {
            color: #2f3640;
            margin-bottom: 25px;
        }

        .option-btn {
            display: block;
            width: 100%;
            margin: 10px 0;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.2s ease;
        }

        .option-btn:hover {
            background-color: #0056b3;
        }

        .logout-btn {
            background-color: #e84118;
        }

        .logout-btn:hover {
            background-color: #c23616;
        }

        .welcome {
            font-size: 18px;
            color: #444;
            margin-bottom: 25px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Roommate Dashboard</h2>

    <%-- Display the logged-in user’s name from session (set by AuthServlet) --%>
    <div class="welcome">
        Welcome,
        <strong>
            <%= (session.getAttribute("username") != null)
                    ? session.getAttribute("username")
                    : "Guest" %>
        </strong>!
    </div>

    <%-- Button to go to chores page --%>
    <form action="chore.jsp" method="get">
        <input type="submit" class="option-btn" value="Manage Chores">
    </form>

    <%-- Button to go to expenses page --%>
    <form action="Expense.jsp" method="get">
        <input type="submit" class="option-btn" value="Split Expenses">
    </form>

    <%-- Button to log out --%>
    <form action="LogoutServlet" method="post">
        <input type="submit" class="option-btn logout-btn" value="Logout">
    </form>
</div>

</body>
</html>
