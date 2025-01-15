<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quản lý Sản phẩm</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-manage-product.css">
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- Sidebar -->
    <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar">
      <div class="position-sticky">
        <h3 class="text-center my-3">Admin Panel</h3>
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/index.jsp' />">Trang chủ</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="<c:url value='/admin/manage-product.jsp' />">Quản lý sản phẩm</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/manage-promotion.jsp' />">Quản lý chương trình khuyến mãi</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/manage-review.jsp' />">Quản lý đánh giá sản phẩm</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/manage-order.jsp' />">Quản lý đơn hàng</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/statistics.jsp' />">Thống kê và báo cáo doanh thu</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/manage-user.jsp' />">Quản lý người dùng</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/feedback.jsp' />">Phản hồi khách hàng</a>
          </li>
        </ul>
      </div>
    </nav>

    <!-- Main content -->
    <div class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Quản lý sản phẩm</h1>
      </div>
      <div class="main-content">
        <table class="table table-striped table-bordered">
          <div class="d-flex justify-content-end">
            <button class="btn btn-sm btn-success btn-lg" data-bs-toggle="modal" data-bs-target="#addModal">Thêm Sản Phẩm</button>
          </div>
          <thead>
          <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Link ảnh</th>
            <th>Mô tả</th>
            <th>Nhà cung cấp</th>
            <th>Phân loại</th>
            <th>Trạng thái hoạt động</th>
            <th>Hành động</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="product" items="${productList}">
            <tr>
              <td>${product.id}</td>
              <td>${product.name}</td>
              <td>${product.price}đ</td>
              <td>${product.quantity}</td>
              <td><img src="${product.imageLink}" alt="${product.name}" width="50"></td>
              <td class="short-description">${product.description}</td>
              <td>${product.supplier}</td>
              <td>${product.category}</td>
              <td>${product.status}</td>
              <td>
                <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editModal" data-id="${product.id}">Sửa</button>
                <button class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" data-id="${product.id}">Xóa</button>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- Modal Thêm sản phẩm -->
<div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addModalLabel">Thêm sản phẩm</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/addProduct" method="post">
          <!-- Form fields -->
          <button type="submit" class="btn btn-primary">Áp dụng</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="<c:url value='/js/manage-product.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

