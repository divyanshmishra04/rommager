<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logged Out</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: #f4f6f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .logout-container {
            background: #fff;
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 {
            color: #2c3e50;
            margin-bottom: 20px;
        }
        p {
            color: #7f8c8d;
            margin-bottom: 30px;
        }
        a {
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            padding: 12px 20px;
            border-radius: 8px;
            transition: background 0.3s;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="logout-container">
    <h1>You’ve been logged out successfully!</h1>
    <p>Thank you for using RoomMate Manager.</p>
    <a href="index.jsp">Login Again</a>
</div>
</body>
</html>
