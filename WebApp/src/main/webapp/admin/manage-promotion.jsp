<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý chương trình khuyến mãi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
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
                        <a class="nav-link" href="<c:url value='/admin/home' />">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/admin/manage-product.jsp' />">Quản lý sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="<c:url value='/admin/manage-promotion.jsp' />">Quản lý chương trình khuyến mãi</a>
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
                <h1 class="h2">Quản lý chương trình khuyến mãi</h1>
            </div>

            <div class="overview">
                <h2>Danh sách chương trình khuyến mãi</h2>
                <p>Admin có thể thêm, sửa hoặc xóa các chương trình khuyến mãi tại đây.</p>
                <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addPromotionModal">Thêm chương trình khuyến mãi</button>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Tên chương trình</th>
                        <th>Mô tả</th>
                        <th>Giảm giá (%)</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="promotion" items="${promotionList}">
                        <tr>
                            <td>${promotion.id}</td>
                            <td>${promotion.name}</td>
                            <td>${promotion.description}</td>
                            <td>${promotion.discount}%</td>
                            <td>${promotion.startDate}</td>
                            <td>${promotion.endDate}</td>
                            <td>
                                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editPromotionModal" data-id="${promotion.id}">Sửa</button>
                                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deletePromotionModal" data-id="${promotion.id}">Xóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Modal thêm chương trình khuyến mãi -->
<div class="modal" id="addPromotionModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm chương trình khuyến mãi</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/addPromotion" method="post">
                    <div class="mb-3">
                        <label for="promotionName" class="form-label">Tên chương trình</label>
                        <input type="text" class="form-control" id="promotionName" name="promotionName" required>
                    </div>
                    <div class="mb-3">
                        <label for="promotionDescription" class="form-label">Mô tả</label>
                        <input type="text" class="form-control" id="promotionDescription" name="promotionDescription" required>
                    </div>
                    <div class="mb-3">
                        <label for="promotionDiscount" class="form-label">Giảm giá (%)</label>
                        <input type="number" class="form-control" id="promotionDiscount" name="promotionDiscount" required>
                    </div>
                    <div class="mb-3">
                        <label for="startDate" class="form-label">Ngày bắt đầu</label>
                        <input type="date" class="form-control" id="startDate" name="startDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="endDate" class="form-label">Ngày kết thúc</label>
                        <input type="date" class="form-control" id="endDate" name="endDate" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Thêm</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal Sửa Chương Trình Khuyến Mãi -->
<div class="modal fade" id="editPromotionModal" tabindex="-1" aria-labelledby="editPromotionModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editPromotionModalLabel">Sửa chương trình khuyến mãi</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/editPromotion" method="post">
                    <input type="hidden" id="editPromotionId" name="promotionId">
                    <div class="mb-3">
                        <label for="editPromotionName" class="form-label">Tên chương trình</label>
                        <input type="text" class="form-control" id="editPromotionName" name="promotionName" required>
                    </div>
                    <div class="mb-3">
                        <label for="editPromotionDescription" class="form-label">Mô tả</label>
                        <textarea class="form-control" id="editPromotionDescription" name="promotionDescription" rows="3" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="editPromotionDiscount" class="form-label">Giảm giá (%)</label>
                        <input type="number" class="form-control" id="editPromotionDiscount" name="promotionDiscount" required>
                    </div>
                    <div class="mb-3">
                        <label for="editStartDate" class="form-label">Ngày bắt đầu</label>
                        <input type="date" class="form-control" id="editStartDate" name="startDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="editEndDate" class="form-label">Ngày kết thúc</label>
                        <input type="date" class="form-control" id="editEndDate" name="endDate" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Cập nhật chương trình</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal Xóa Chương Trình Khuyến Mãi -->
<div class="modal fade" id="deletePromotionModal" tabindex="-1" aria-labelledby="deletePromotionModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deletePromotionModalLabel">Xóa chương trình khuyến mãi</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn muốn xóa chương trình khuyến mãi này không? Hành động này sẽ không thể phục hồi.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-danger" data-id="${promotion.id}">Xóa</button>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value='/js/manage-promotion.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
