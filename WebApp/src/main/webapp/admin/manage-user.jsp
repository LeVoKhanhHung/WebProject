<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Người Dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/admin-index.css' />">
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
                        <a class="nav-link" href="<c:url value='/admin/manage-product.jsp' />">Quản lý sản phẩm</a>
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
                        <a class="nav-link active" href="<c:url value='/admin/manage-user.jsp' />">Quản lý người dùng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/admin/feedback.jsp' />">Phản hồi khách hàng</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Main content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Quản Lý Người Dùng</h1>
            </div>

            <!-- Danh sách người dùng -->
            <div class="overview">
                <h2>Danh Sách Người Dùng</h2>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Tên</th>
                        <th>Email</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>${user.status}</td>
                            <td>
                                <button class="btn btn-warning btn-sm" data-bs-toggle="modal"
                                        data-bs-target="#editUserModal" onclick="editUser(${user.id})">Chỉnh sửa
                                </button>
                                <button class="btn btn-danger btn-sm" onclick="toggleStatus(${user.id})">Khóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>

<!-- Modal chỉnh sửa thông tin -->
<div class="modal fade" id="editUserModal" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editUserModalLabel">Chỉnh sửa thông tin người dùng</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="editUserForm">
                    <div class="mb-3">
                        <label for="editUserName" class="form-label">Tên</label>
                        <input type="text" class="form-control" id="editUserName" required>
                    </div>
                    <div class="mb-3">
                        <label for="editUserEmail" class="form-label">Email</label>
                        <input type="email" class="form-control" id="editUserEmail" required>
                    </div>
                    <div class="mb-3">
                        <label for="editUserStatus" class="form-label">Trạng thái</label>
                        <select class="form-select" id="editUserStatus">
                            <option value="Đang hoạt động">Đang hoạt động</option>
                            <option value="Bị khóa">Bị khóa</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <button type="button" class="btn btn-primary" id="saveEditButton" onclick="saveUserChanges()">Lưu thay
                    đổi
                </button>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value='/js/manage-user.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
