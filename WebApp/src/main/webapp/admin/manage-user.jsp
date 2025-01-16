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
        <% request.setAttribute("activePage", "manage-user"); %>
        <jsp:include page="sidebar.jsp" />

        <!-- Main content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Quản Lý Người Dùng</h1>
            </div>

            <!-- Danh sách người dùng -->
            <div class="overview">
                <h2>Danh Sách Người Dùng</h2>

                <c:if test="${empty userList}">
                    <p>Không có người dùng nào.</p>
                </c:if>

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
                            <td>${user.userName}</td>
                            <td>${user.email}</td>
                            <td>${user.isActive}</td>
                            <td>
                                <!-- Form để thay đổi trạng thái người dùng -->
                                <form action="/admin/manage-user" method="post" style="display: inline;">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="action" value="${user.isActive ? 'deactivate' : 'activate'}">
                                    <button class="btn ${user.isActive ? 'btn-warning' : 'btn-success'} btn-sm" type="submit">
                                            ${user.isActive ? 'Khóa' : 'Kích hoạt'}
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>

<script src="<c:url value='/js/manage-user.js' />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
