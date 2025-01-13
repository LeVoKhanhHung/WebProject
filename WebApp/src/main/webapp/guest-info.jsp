<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Thông Tin Cá Nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/guest-info.css">
</head>
<body>
<!-- Header -->
<jsp:include page="header.jsp"/>

<!-- Content -->
<section class="content">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="profile-card p-4 border rounded-3">
                    <!-- Thông tin cá nhân và ảnh đại diện -->
                    <div class="profile-container">
                        <div class="container mt-4">
                            <div class="row align-items-center">
                                <!-- Ảnh đại diện -->
                                <div class="col-md-4 text-center">
                                    <img src="${user.profileImage != null ? user.profileImage : 'https://via.placeholder.com/200x200'}"
                                         alt="Profile Image" class="profile-image" id="profile-image"
                                         onclick="changeProfileImage()">
                                </div>
                                <!-- Thông tin cá nhân -->
                                <div class="col-md-8">
                                    <h5>Thông tin cá nhân</h5>
                                    <h3 id="user-name">${user.fullName}</h3> <!-- userName -->
                                    <p id="user-email">${user.email}</p> <!-- email -->
                                    <ul class="list-unstyled">
                                        <li><strong>Địa chỉ:</strong> <span id="user-address">${user.address}</span>
                                        </li> <!-- address -->
                                        <li><strong>Số điện thoại:</strong> <span id="user-phone">${user.phone}</span>
                                        </li> <!-- phoneNumber -->
                                        <li><strong>Ngày sinh:</strong> <span
                                                id="user-birthdate">${user.birthDate}</span></li> <!-- birthDate -->
                                        <li><strong>Công ty:</strong> <span id="user-company">${user.company}</span>
                                        </li> <!-- companyName -->
                                    </ul>
                                    <button class="btn btn-primary me-2" data-bs-toggle="modal"
                                            data-bs-target="#editProfileModal" onclick="loadProfileIntoModal()">Chỉnh
                                        sửa thông tin
                                    </button>
                                    <button class="btn btn-danger" data-bs-toggle="modal"
                                            data-bs-target="#changePasswordModal">Thay đổi mật khẩu
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>

                    <!-- Lịch sử đơn hàng -->
                    <h5>Lịch sử đơn hàng</h5>
                    <ul class="list-group">
                        <c:forEach var="order" items="${orderHistory}">
                            <li class="list-group-item">Đơn hàng #${order.id} - Ngày: ${order.date}</li>
                        </c:forEach>
                    </ul>
                </div>

                <!-- Giỏ hàng và Wishlist -->
                <div class="section-container row mt-4">
                    <div class="col-md-6">
                        <div class="container border rounded-3 p-3">
                            <h5>Giỏ hàng</h5>
                            <p>Danh sách sản phẩm trong giỏ hàng.</p>
                            <button class="btn btn-success" onclick="window.location.href='shopping-cart.jsp'">Đi đến
                                Giỏ hàng
                            </button>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="container border rounded-3 p-3">
                            <h5>Wishlist</h5>
                            <p>Danh sách sản phẩm yêu thích.</p>
                            <button class="btn btn-warning" onclick="window.location.href='wishlist.jsp'">Đi đến
                                Wishlist
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Chỉnh sửa thông tin cá nhân -->
    <div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editProfileModalLabel">Chỉnh sửa thông tin cá nhân</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="editProfileForm">
                        <div class="mb-3">
                            <label for="editName" class="form-label">Tên</label>
                            <input type="text" class="form-control" id="editName" value="${user.fullName}">
                        </div>
                        <div class="mb-3">
                            <label for="editEmail" class="form-label">Email</label>
                            <input type="email" class="form-control" id="editEmail" value="${user.email}">
                        </div>
                        <div class="mb-3">
                            <label for="editPhone" class="form-label">Số điện thoại</label>
                            <input type="text" class="form-control" id="editPhone" value="${user.phone}" maxlength="10">
                        </div>
                        <div class="mb-3">
                            <label for="editBirthdate" class="form-label">Ngày sinh</label>
                            <input type="date" class="form-control" id="editBirthdate" value="${user.birthDate}">
                        </div>
                        <div class="mb-3">
                            <label for="editCompany" class="form-label">Công ty</label>
                            <input type="text" class="form-control" id="editCompany" value="${user.company}">
                        </div>
                        <div class="mb-3">
                            <label for="editAddress" class="form-label">Địa chỉ</label>
                            <input type="text" class="form-control" id="editAddress" value="${user.address}">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-primary" onclick="changeProfile()">Lưu thay đổi</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Thay đổi mật khẩu -->
    <div class="modal fade" id="changePasswordModal" tabindex="-1" aria-labelledby="changePasswordModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="changePasswordModalLabel">Thay đổi mật khẩu</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="changePasswordForm">
                        <div class="mb-3">
                            <label for="oldPassword" class="form-label">Mật khẩu cũ</label>
                            <input type="password" class="form-control" id="oldPassword" required>
                        </div>
                        <div class="mb-3">
                            <label for="newPassword" class="form-label">Mật khẩu mới</label>
                            <input type="password" class="form-control" id="newPassword" required>
                        </div>
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Xác nhận mật khẩu mới</label>
                            <input type="password" class="form-control" id="confirmPassword" required>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-primary" onclick="changePassword()">Lưu thay đổi</button>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<jsp:include page="footer.jsp"/>

<!-- Script-->
<script src="/js/guest-info.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
