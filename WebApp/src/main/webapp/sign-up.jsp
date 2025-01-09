<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="vi">--%>
<%--<head>--%>
<%--  <meta charset="UTF-8">--%>
<%--  <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--  <title>Đăng Ký</title>--%>
<%--  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">--%>
<%--  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign-up.css">--%>
<%--</head>--%>
<%--<body class="d-flex justify-content-center align-items-center vh-100" style="background-color: antiquewhite;">--%>
<%--<!-- Form Đăng ký-->--%>
<%--<div class="signin-form bg-white p-4 shadow-sm rounded-3" style="max-width: 400px; width: 100%;">--%>
<%--  <h2 class="text-center text-primary mb-4">Đăng Ký</h2>--%>
<%--  <form action="register" method="POST">--%>
<%--    <div class="mb-3">--%>
<%--      <label for="fullname" class="form-label">Họ và Tên</label>--%>
<%--      <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Nhập họ và tên">--%>
<%--    </div>--%>
<%--    <div class="mb-3">--%>
<%--      <label for="email" class="form-label">Email</label>--%>
<%--      <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email">--%>
<%--    </div>--%>
<%--    <div class="mb-3">--%>
<%--      <label for="password" class="form-label">Mật Khẩu</label>--%>
<%--      <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu">--%>
<%--    </div>--%>
<%--    <div class="mb-3">--%>
<%--      <label for="confirm-password" class="form-label">Xác Nhận Mật Khẩu</label>--%>
<%--      <input type="password" class="form-control" id="confirm-password" name="confirm-password" placeholder="Nhập lại mật khẩu">--%>
<%--    </div>--%>
<%--    <div class="mb-3">--%>
<%--      <label for="phoneNumber" class="form-label">Số Điện Thoại</label>--%>
<%--      <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Nhập số điện thoại">--%>
<%--    </div>--%>
<%--    <div class="mb-3">--%>
<%--      <label for="birthDate" class="form-label">Ngày Sinh</label>--%>
<%--      <input type="date" class="form-control" id="birthDate" name="birthDate">--%>
<%--    </div>--%>
<%--    <div class="mb-3">--%>
<%--      <label for="companyName" class="form-label">Tên Công Ty</label>--%>
<%--      <input type="text" class="form-control" id="companyName" name="companyName" placeholder="Nhập tên công ty">--%>
<%--    </div>--%>
<%--    <div class="mb-3">--%>
<%--      <label for="address" class="form-label">Địa Chỉ</label>--%>
<%--      <input type="text" class="form-control" id="address" name="address" placeholder="Nhập địa chỉ">--%>
<%--    </div>--%>
<%--    <button type="submit" class="btn btn-primary w-100">Đăng Ký</button>--%>
<%--    <p class="text-center mt-3">Đã có tài khoản? <a href="login.jsp">Chuyển sang đăng nhập</a></p>--%>
<%--  </form>--%>
<%--</div>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đăng Ký</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign-up.css">
</head>
<body class="d-flex justify-content-center align-items-center vh-100" style="background-color: antiquewhite;">
<!-- Form Đăng ký-->
<div class="signin-form bg-white p-4 shadow-sm rounded-3" style="max-width: 600px; width: 100%;">
  <h2 class="text-center text-primary mb-4">Đăng Ký</h2>
  <form action="register" method="POST">
    <div class="row mb-3">
      <div class="col-md-6">
        <label for="fullname" class="form-label">Họ và Tên</label>
        <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Nhập họ và tên">
      </div>
      <div class="col-md-6">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-md-6">
        <label for="password" class="form-label">Mật Khẩu</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu">
      </div>
      <div class="col-md-6">
        <label for="confirm-password" class="form-label">Xác Nhận Mật Khẩu</label>
        <input type="password" class="form-control" id="confirm-password" name="confirm-password" placeholder="Nhập lại mật khẩu">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-md-6">
        <label for="phoneNumber" class="form-label">Số Điện Thoại</label>
        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Nhập số điện thoại">
      </div>
      <div class="col-md-6">
        <label for="birthDate" class="form-label">Ngày Sinh</label>
        <input type="date" class="form-control" id="birthDate" name="birthDate">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-md-6">
        <label for="companyName" class="form-label">Tên Công Ty</label>
        <input type="text" class="form-control" id="companyName" name="companyName" placeholder="Nhập tên công ty">
      </div>
      <div class="col-md-6">
        <label for="address" class="form-label">Địa Chỉ</label>
        <input type="text" class="form-control" id="address" name="address" placeholder="Nhập địa chỉ">
      </div>
    </div>
    <button type="submit" class="btn btn-primary w-100">Đăng Ký</button>
    <p class="text-center mt-3">Đã có tài khoản? <a href="login.jsp">Chuyển sang đăng nhập</a></p>
  </form>
</div>
</body>
</html>

