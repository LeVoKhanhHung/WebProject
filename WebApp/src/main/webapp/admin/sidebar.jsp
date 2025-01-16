<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar">
    <div class="position-sticky">
        <h3 class="text-center my-3">Admin Panel</h3>
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link ${activePage == 'home' ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/home">Trang chủ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${activePage == 'manage-product' ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/manage-product.jsp">Quản lý sản phẩm</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${activePage == 'manage-promotion' ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/manage-promotion">Quản lý chương trình khuyến mãi</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${activePage == 'manage-review' ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/manage-review.jsp">Quản lý đánh giá sản phẩm</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${activePage == 'manage-order' ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/manage-order.jsp">Quản lý đơn hàng</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${activePage == 'statistics' ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/statistics.jsp">Thống kê và báo cáo doanh thu</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${activePage == 'manage-user' ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/manage-user">Quản lý người dùng</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${activePage == 'feedback' ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/feedback">Phản hồi khách hàng</a>
            </li>
        </ul>
    </div>
</nav>
