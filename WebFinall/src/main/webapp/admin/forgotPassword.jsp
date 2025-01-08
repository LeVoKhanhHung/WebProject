<%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 15/12/2024
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên Mật Khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/admin-forgot-password.css">
</head>
<body>
<div class="container">
    <div class="form-container">
        <h2 class="text-center mb-4">Quên Mật Khẩu</h2>
        <form action="../forgotPassword" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" required name="email">
            </div>
            <button type="submit" class="btn btn-primary w-100">Gửi Yêu Cầu</button>
        </form>
    </div>
</div>
</body>
</html>
