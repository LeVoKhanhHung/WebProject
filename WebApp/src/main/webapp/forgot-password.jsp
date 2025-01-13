<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên Mật Khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100" style="background-color: antiquewhite;">
<div class="card shadow-sm p-4 w-100" style="max-width: 400px;">
    <h2 class="text-center text-primary mb-4">Quên Mật Khẩu</h2>

    <!-- Hiển thị thông báo lỗi hoặc thành công -->
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        String successMessage = (String) request.getAttribute("successMessage");
        if (errorMessage != null) {
    %>
    <div class="alert alert-danger" role="alert">
        <%= errorMessage %>
    </div>
    <%
        }
        if (successMessage != null) {
    %>
    <div class="alert alert-success" role="alert">
        <%= successMessage %>
    </div>
    <%
        }
    %>

    <!-- Form xử lý quên mật khẩu -->
    <form action="ForgotPasswordController" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email để lấy lại mật khẩu" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Lấy Lại Mật Khẩu</button>
        <p class="text-center mt-3">
            Quay lại <a href="login.jsp" class="text-primary">Đăng nhập</a>
        </p>
    </form>
</div>

<!-- Bootstrap Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
