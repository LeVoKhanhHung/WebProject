<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quản lý đơn hàng</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="<c:url value='/css/admin-index.css'/>">
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- Sidebar -->
    <jsp:include page="admin-sidebar.jsp" />

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
          <c:forEach var="order" items="${orderList}">
            <tr>
              <td>${order.index}</td>
              <td>${order.orderId}</td>
              <td>${order.customerName}</td>
              <td>${order.products}</td>
              <td>${order.totalPrice} VND</td>
              <td>${order.status}</td>
              <td>${order.orderDate}</td>
              <td>
                <button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#viewOrderModal-${order.orderId}">Xem</button>
                <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#updateStatusModal-${order.orderId}">Cập nhật trạng thái</button>
                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteOrderModal-${order.orderId}">Xóa</button>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </div>
</div>

<!-- Modal Xem Chi Tiết Đơn Hàng -->
<c:forEach var="order" items="${orderList}">
  <div class="modal fade" id="viewOrderModal-${order.orderId}" tabindex="-1" aria-labelledby="viewOrderModalLabel-${order.orderId}" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="viewOrderModalLabel-${order.orderId}">Chi tiết đơn hàng</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <h6>Mã đơn hàng: ${order.orderId}</h6>
          <p><strong>Khách hàng:</strong> ${order.customerName}</p>
          <p><strong>Sản phẩm:</strong> ${order.products}</p>
          <p><strong>Tổng tiền:</strong> ${order.totalPrice} VND</p>
          <p><strong>Trạng thái:</strong> ${order.status}</p>
          <p><strong>Ngày đặt:</strong> ${order.orderDate}</p>
          <p><strong>Địa chỉ giao hàng:</strong> ${order.shippingAddress}</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
        </div>
      </div>
    </div>
  </div>
</c:forEach>

<!-- Modal Cập Nhật Trạng Thái Đơn Hàng -->
<c:forEach var="order" items="${orderList}">
  <div class="modal fade" id="updateStatusModal-${order.orderId}" tabindex="-1" aria-labelledby="updateStatusModalLabel-${order.orderId}" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="updateStatusModalLabel-${order.orderId}">Cập nhật trạng thái đơn hàng</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form action="updateOrderStatus" method="post">
            <input type="hidden" name="orderId" value="${order.orderId}">
            <div class="mb-3">
              <label for="orderStatus-${order.orderId}" class="form-label">Trạng thái đơn hàng</label>
              <select class="form-select" id="orderStatus-${order.orderId}" name="status">
                <option value="Đang xử lý" ${order.status == 'Đang xử lý' ? 'selected' : ''}>Đang xử lý</option>
                <option value="Đang giao hàng" ${order.status == 'Đang giao hàng' ? 'selected' : ''}>Đang giao hàng</option>
                <option value="Hoàn thành" ${order.status == 'Hoàn thành' ? 'selected' : ''}>Hoàn thành</option>
                <option value="Đã hủy" ${order.status == 'Đã hủy' ? 'selected' : ''}>Đã hủy</option>
              </select>
            </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            <button type="submit" class="btn btn-primary">Cập nhật trạng thái</button>
          </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</c:forEach>

<!-- Modal Xóa Đơn Hàng -->
<c:forEach var="order" items="${orderList}">
  <div class="modal fade" id="deleteOrderModal-${order.orderId}" tabindex="-1" aria-labelledby="deleteOrderModalLabel-${order.orderId}" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="deleteOrderModalLabel-${order.orderId}">Xóa đơn hàng</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          Bạn có chắc chắn muốn xóa đơn hàng <strong>${order.orderId}</strong> không? Hành động này sẽ không thể phục hồi.
        </div>
        <div class="modal-footer">
          <form action="deleteOrder" method="post">
            <input type="hidden" name="orderId" value="${order.orderId}">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            <button type="submit" class="btn btn-danger">Xóa</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</c:forEach>

<script src="<c:url value='/js/bootstrap.bundle.min.js'/>"></script>
</body>
</html>

