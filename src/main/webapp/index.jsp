<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>RoomMate Manager | Login</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: #f4f6f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background: #ffffff;
            padding: 40px 50px;
            border-radius: 20px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            width: 350px;
            text-align: center;
        }
        h1 {
            color: #2f3640;
            font-size: 24px;
            margin-bottom: 20px;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px;
            margin: 8px 0;
            border-radius: 8px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px;
            border: none;
            width: 100%;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error {
            color: #e74c3c;
            margin-bottom: 15px;
            font-size: 14px;
        }
        footer {
            margin-top: 20px;
            color: #7f8c8d;
            font-size: 13px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h1>RoomMate Manager</h1>

        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
            <div class="error"><%= error %></div>
        <% } %>

        <form action="AuthServlet" method="post">
            <input type="text" name="username" placeholder="Enter your name" required>
            <input type="password" name="password" placeholder="Enter shared password" required>
            <input type="submit" value="Login">
        </form>

        <footer>
            <p> Manage chores, expenses, and absences easily</p>
        </footer>
    </div>
</body>
</html>
