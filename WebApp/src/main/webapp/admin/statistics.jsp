<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thống kê và báo cáo doanh thu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin-index.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>
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
                        <a class="nav-link" href="<c:url value='/admin/manage-promotion.jsp' />">Quản lý chương trình khuyến mãi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/admin/manage-review.jsp' />">Quản lý đánh giá sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/admin/manage-order.jsp' />">Quản lý đơn hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="<c:url value='/admin/statistics.jsp' />">Thống kê và báo cáo doanh thu</a>
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
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Thống kê và báo cáo doanh thu</h1>
            </div>

            <!-- Revenue Statistics Overview -->
            <div class="overview mb-4">
                <h2>Tổng quan doanh thu</h2>
                <p>Chào mừng bạn đến với trang báo cáo doanh thu, tại đây bạn có thể xem tổng doanh thu, sản phẩm bán chạy và các báo cáo khác.</p>

                <!-- Total Revenue -->
                <div class="row">
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card shadow-sm">
                            <div class="card-body text-center">
                                <h5 class="card-title">Tổng Doanh Thu</h5>
                                <p class="card-text" style="font-size: 1.5rem;">
                                    <%= request.getAttribute("totalRevenue") != null ? request.getAttribute("totalRevenue") : "0 VND" %>
                                </p>
                            </div>
                        </div>
                    </div>
                    <!-- Add more metrics like top products, etc -->
                </div>
            </div>

            <!-- Product Sales Chart -->
            <div class="overview mb-4">
                <h2>Biểu đồ doanh thu theo sản phẩm</h2>
                <canvas id="productSalesChart" width="400" height="200"></canvas>
            </div>

            <!-- Monthly Revenue Chart -->
            <div class="overview mb-4">
                <h2>Doanh thu theo tháng</h2>
                <canvas id="monthlyRevenueChart" width="400" height="200"></canvas>
            </div>

        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="<c:url value='/js/statistics.js' />"></script>
</body>
</html>

