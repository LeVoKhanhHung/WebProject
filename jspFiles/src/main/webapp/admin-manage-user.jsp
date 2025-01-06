<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Người Dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-index.css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <jsp:include page="admin-sidebar.jsp" />

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
                    <tr>
                        <td>1</td>
                        <td>Nguyễn Văn A</td>
                        <td>a@example.com</td>
                        <td>Đang hoạt động</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editUser(1)">Chỉnh sửa</button>
                            <button class="btn btn-danger btn-sm" onclick="toggleStatus(1)">Khóa</button>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Trần Thị B</td>
                        <td>b@example.com</td>
                        <td>Đang hoạt động</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editUser(2)">Chỉnh sửa</button>
                            <button class="btn btn-danger btn-sm" onclick="toggleStatus(2)">Khóa</button>
                        </td>
                    </tr>
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
                <button type="button" class="btn btn-primary" id="saveEditButton">Lưu thay đổi</button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/manage-user.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
