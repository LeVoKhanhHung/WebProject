<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="d-flex flex-column flex-shrink-0 p-3 bg-light" style="width: 250px; height: 100vh;">
    <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
        <span class="fs-4">Admin Panel</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="<%= request.getContextPath() %>/admin-index.jsp" class="nav-link active">Dashboard</a>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/admin-product-management.jsp" class="nav-link link-dark">Quản lý sản phẩm</a>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/admin-order-management.jsp" class="nav-link link-dark">Quản lý đơn hàng</a>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/admin-user-management.jsp" class="nav-link link-dark">Quản lý người dùng</a>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/admin-report.jsp" class="nav-link link-dark">Thống kê</a>
        </li>
    </ul>
    <hr>
    <div class="dropdown">
        <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle" id="dropdownUser" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="<%= request.getContextPath() %>/images/avatar.png" alt="Admin Avatar" width="32" height="32" class="rounded-circle me-2">
            <strong>Admin</strong>
        </a>
        <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser">
            <li><a class="dropdown-item" href="<%= request.getContextPath() %>/admin-profile.jsp">Hồ sơ</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="<%= request.getContextPath() %>/logout">Đăng xuất</a></li>
        </ul>
    </div>
</div>
