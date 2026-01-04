<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Roommate Expense Splitter</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f6f8fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            width: 450px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        input[type="text"], input[type="number"] {
            width: 90%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        input[type="submit"], button {
            background: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 6px;
            cursor: pointer;
        }

        input[type="submit"]:hover, button:hover {
            background: #0056b3;
        }

        .result {
            background: #e9f7ef;
            border: 1px solid #b2dfdb;
            padding: 10px;
            border-radius: 6px;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Expense Calculator</h2>

    <!-- First form: Takes item names and prices -->
    <form action="ExpenseServlet" method="post">
        <h4>Enter items and their prices:</h4>

        <!-- You can add as many as needed -->
        <input type="text" name="item" placeholder="Item 1 name">
        <input type="number" name="price" placeholder="Price 1"><br>

        <input type="text" name="item" placeholder="Item 2 name">
        <input type="number" name="price" placeholder="Price 2"><br>

        <input type="text" name="item" placeholder="Item 3 name">
        <input type="number" name="price" placeholder="Price 3"><br>

        <input type="submit" name="action" value="Calculate Total">
    </form>

    <%-- Display total if calculated --%>
    <%
        Double total = (Double) request.getAttribute("total");
        if (total != null) {
    %>
    <div class="result">
        <p><strong>Total Expense:</strong> ₹<%= total %></p>
        <form action="ExpenseServlet" method="post">
            <input type="hidden" name="total" value="<%= total %>">
            <label>Enter number of roommates:</label><br>
            <input type="number" name="roommates" min="1" required>
            <input type="submit" name="action" value="Split Expense">
        </form>
    </div>
    <% } %>

    <%-- Display per-person share if available --%>
    <%
        Double perPerson = (Double) request.getAttribute("perPerson");
        if (perPerson != null) {
    %>
    <div class="result">
        <p><strong>Each roommate should pay:</strong> ₹<%= perPerson %></p>
    </div>
    <% } %>

    <div class="back">
            <a href="dashboard.jsp">← Back to Dashboard</a>
        </div>
    </div>

</div>

</body>
</html>
