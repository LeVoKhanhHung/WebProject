<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar">
  <div class="position-sticky">
    <h3 class="text-center my-3">Admin Panel</h3>
    <ul class="nav flex-column">
      <li class="nav-item">
        <a class="nav-link active" href="<c:url value='/admin/index.jsp' />">Trang chủ</a>
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
        <a class="nav-link" href="<c:url value='/admin/manage-user.jsp' />">Quản lý người dùng</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='/admin/feedback.jsp' />">Phản hồi khách hàng</a>
      </li>
    </ul>
  </div>
</nav>

