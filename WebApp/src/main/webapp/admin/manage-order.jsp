<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý đơn hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/admin-index.css' />">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <!-- Sidebar -->
        <% request.setAttribute("activePage", "manage-order"); %>
        <jsp:include page="sidebar.jsp" />

        <!-- Main content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <h1 class="h2">Quản lý đơn hàng</h1>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Mã đơn hàng</th>
                    <th>Tên khách hàng</th>
                    <th>Tổng tiền</th>
                    <th>Đã thanh toán</th>
                    <th>Ngày đặt</th>
                    <th>Ngày nhận</th>
                    <th>Địa chỉ nhận</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.idUser}</td>
                        <td>${order.customerName}</td>
                        <td>${order.totalPrice} VND</td>
                        <td><c:choose>
                            <c:when test="${order.isPaid}">Đã thanh toán</c:when>
                            <c:otherwise>Chưa thanh toán</c:otherwise>
                        </c:choose></td>
                        <td>${order.createDate}</td>
                        <td>${order.receiveDate}</td>
                        <td>${order.receiveAddress}</td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/order">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <button name="action" value="update" class="btn btn-warning btn-sm">Cập nhật</button>
                                <button name="action" value="delete" class="btn btn-danger btn-sm">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</div>
<script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
</body>
</html>
