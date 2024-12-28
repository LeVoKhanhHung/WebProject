<%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 15/12/2024
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý đơn hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/admin-index.css">
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
                        <a class="nav-link" href="index.html">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-product.jsp">Quản lý sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-promotion.html">Quản lý chương trình khuyến mãi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-review.html">Quản lý đánh giá sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="manage-order.html">Quản lý đơn hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="statistics.html">Thống kê và báo cáo doanh thu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-user.html">Quản lý người dùng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="feedback.html">Phản hồi khách hàng</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Main content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Quản lý đơn hàng</h1>
            </div>

            <div class="overview">
                <h2>Danh sách đơn hàng</h2>
                <p>Admin có thể xem chi tiết, cập nhật trạng thái hoặc xóa đơn hàng tại đây.</p>

                <!-- Table for Orders -->
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Mã đơn hàng</th>
                        <th>Tên khách hàng</th>
                        <th>Sản phẩm</th>
                        <th>Tổng tiền</th>
                        <th>Trạng thái</th>
                        <th>Ngày đặt</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>#ORD001</td>
                        <td>Nguyễn Văn A</td>
                        <td>Nấm Linh Chi, Nấm Cordyceps</td>
                        <td>1,500,000 VND</td>
                        <td>Đang xử lý</td>
                        <td>2024-11-05</td>
                        <td>
                            <button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#viewOrderModal">Xem</button>
                            <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#updateStatusModal">Cập nhật trạng thái</button>
                            <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteOrderModal">Xóa</button>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>#ORD002</td>
                        <td>Trần Thị B</td>
                        <td>Nấm Linh Chi</td>
                        <td>500,000 VND</td>
                        <td>Đang giao hàng</td>
                        <td>2024-11-06</td>
                        <td>
                            <button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#viewOrderModal">Xem</button>
                            <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#updateStatusModal">Cập nhật trạng thái</button>
                            <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteOrderModal">Xóa</button>
                        </td>
                    </tr>
                    <!-- Add more orders as needed -->
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>

<!-- Modal Xem Chi Tiết Đơn Hàng -->
<div class="modal fade" id="viewOrderModal" tabindex="-1" aria-labelledby="viewOrderModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="viewOrderModalLabel">Chi tiết đơn hàng</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h6>Mã đơn hàng: #ORD001</h6>
                <p><strong>Khách hàng:</strong> Nguyễn Văn A</p>
                <p><strong>Sản phẩm:</strong> Nấm Linh Chi, Nấm Cordyceps</p>
                <p><strong>Tổng tiền:</strong> 1,500,000 VND</p>
                <p><strong>Trạng thái:</strong> Đang xử lý</p>
                <p><strong>Ngày đặt:</strong> 2024-11-05</p>
                <p><strong>Địa chỉ giao hàng:</strong> 123 Đường ABC, Quận 1, TP. HCM</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Cập Nhật Trạng Thái Đơn Hàng -->
<div class="modal fade" id="updateStatusModal" tabindex="-1" aria-labelledby="updateStatusModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateStatusModalLabel">Cập nhật trạng thái đơn hàng</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <label for="orderStatus" class="form-label">Trạng thái đơn hàng</label>
                        <select class="form-select" id="orderStatus">
                            <option value="Đang xử lý">Đang xử lý</option>
                            <option value="Đang giao hàng">Đang giao hàng</option>
                            <option value="Hoàn thành">Hoàn thành</option>
                            <option value="Đã hủy">Đã hủy</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-primary">Cập nhật trạng thái</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Xóa Đơn Hàng -->
<div class="modal fade" id="deleteOrderModal" tabindex="-1" aria-labelledby="deleteOrderModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteOrderModalLabel">Xóa đơn hàng</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn muốn xóa đơn hàng này không? Hành động này sẽ không thể phục hồi.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-danger">Xóa</button>
            </div>
        </div>
    </div>
</div>

<script src="../js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/manage-order.js"></script>
</body>
</html>
