<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đăng Nhập</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css"> -->
</head>
<body class="d-flex justify-content-center align-items-center vh-100" style="background-color: antiquewhite;">
<!-- Form Đăng nhập -->
<div class="card p-4 shadow" style="max-width: 400px; width: 100%;">
  <h2 class="text-center text-primary">Đăng Nhập</h2>
  <form method="post" action="login">
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">Mật Khẩu</label>
      <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
    </div>
    <div class="d-flex justify-content-between">
      <a href="forgot-password.jsp" class="text-decoration-none">Quên mật khẩu?</a>
    </div>
    <button type="submit" class="btn btn-primary w-100 mt-3">Đăng Nhập</button>
    <p class="text-center mt-3">Chưa có tài khoản? <a href="sign-up.jsp" class="text-primary">Đăng ký ngay</a></p>
  </form>
</div>

<!-- Script -->
<!-- <script src="<%= request.getContextPath() %>/js/login.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
