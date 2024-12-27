<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/sign-up.css">
</head>
<body class="d-flex justify-content-center align-items-center vh-100" style="background-color: antiquewhite;">

<%
    // Xử lý logic khi form được submit
    String fullName = request.getParameter("fullname");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirm-password");

    if (request.getMethod().equalsIgnoreCase("POST")) {
        if (password != null && confirmPassword != null && password.equals(confirmPassword)) {
            out.println("<script>alert('Đăng ký thành công!'); window.location.href='login.jsp';</script>");
        } else {
            out.println("<script>alert('Mật khẩu xác nhận không khớp. Vui lòng thử lại!');</script>");
        }
    }
%>

<!-- Form Đăng ký -->
<div class="signin-form bg-white p-4 shadow-sm rounded-3" style="max-width: 400px; width: 100%;">
    <h2 class="text-center text-primary mb-4">Đăng Ký</h2>
    <form method="POST" action="sign-up.jsp">
        <div class="mb-3">
            <label for="fullname" class="form-label">Họ và Tên</label>
            <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Nhập họ và tên" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mật Khẩu</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
        </div>
        <div class="mb-3">
            <label for="confirm-password" class="form-label">Xác Nhận Mật Khẩu</label>
            <input type="password" class="form-control" id="confirm-password" name="confirm-password" placeholder="Nhập lại mật khẩu" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Đăng Ký</button>
        <p class="text-center mt-3">Đã có tài khoản? <a href="login.jsp">Chuyển sang đăng nhập</a></p>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
