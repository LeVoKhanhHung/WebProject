<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="Models.cart.Cart" %><%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 13/12/2024
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }
        img {
            max-width: 100px;
            height: auto;
        }
        .btn-delete {
            color: white;
            background-color: red;
            border: none;
            padding: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>Giỏ hàng của bạn</h1>
<%
    HttpSession session1 = request.getSession(true);
    Cart ca = (Cart) session1.getAttribute("ca");
    if (ca == null) {
        ca = new Cart();
        session1.setAttribute("ca", ca);
    }
    System.out.println(ca.getPrice());
%>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Image</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Total</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="item" items="${ca.items}">
        <tr>
            <td>${item.id}</td>
            <td>${item.nameProduct}</td>
            <td><img src="${item.imgProduct}" alt="${item.nameProduct}" /></td>
            <td>${item.price}</td>
            <td>${item.quantity}</td>
            <td>${item.totalPrice}</td>
            <td><a href="remove?id=${item.id}"><button class="btn-delete">Delete</button></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:set var="price1" value="${ca.price}" />
<h3>Tổng tiền: <span id="total-price">${price1} VND</span></h3>



</body>
</html>