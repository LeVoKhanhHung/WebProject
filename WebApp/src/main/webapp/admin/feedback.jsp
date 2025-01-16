<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Phản Hồi Khách Hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/admin-index.css'/>">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <% request.setAttribute("activePage", "feedback"); %>
        <jsp:include page="sidebar.jsp" />

        <!-- Main content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Phản Hồi Khách Hàng</h1>
            </div>

            <!-- Danh sách tin nhắn từ khách hàng -->
            <div class="overview">
                <h2>Danh Sách Tin Nhắn</h2>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Tên khách hàng</th>
                        <th>Email</th>
                        <th>Tin nhắn</th>
                        <th>Ngày gửi</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="feedback" items="${feedbacks}">
                        <tr>
                            <td>${feedback.id}</td>
                            <td>${feedback.customerName}</td>
                            <td>${feedback.customerEmail}</td>
                            <td>${feedback.comment}</td>
                            <td>${feedback.createDate}</td>
                            <td>
                                <button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#responseModal"
                                        data-id="${feedback.id}" data-name="${feedback.customerName}"
                                        data-email="${feedback.customerEmail}" data-message="${feedback.comment}">
                                    Phản hồi
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Modal phản hồi -->
            <div class="modal fade" id="responseModal" tabindex="-1" aria-labelledby="responseModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="responseModalLabel">Phản hồi khách hàng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="responseForm" action="<c:url value='/admin/feedback/respond'/>" method="post">
                                <input type="hidden" id="feedbackId" name="feedbackId">
                                <div class="mb-3">
                                    <label for="customerName" class="form-label">Tên khách hàng</label>
                                    <input type="text" id="customerName" class="form-control" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="customerEmail" class="form-label">Email khách hàng</label>
                                    <input type="email" id="customerEmail" class="form-control" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="customerMessage" class="form-label">Tin nhắn của khách hàng</label>
                                    <textarea id="customerMessage" class="form-control" rows="4" readonly></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="responseMessage" class="form-label">Tin nhắn phản hồi</label>
                                    <textarea id="responseMessage" name="responseMessage" class="form-control" rows="4" required></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    <button type="submit" class="btn btn-primary">Gửi phản hồi</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<script src="<c:url value='/js/feedback.js'/>"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Xử lý dữ liệu động cho modal
    const responseModal = document.getElementById('responseModal');
    responseModal.addEventListener('show.bs.modal', event => {
        const button = event.relatedTarget;
        const feedbackId = button.getAttribute('data-id');
        const customerName = button.getAttribute('data-name');
        const customerEmail = button.getAttribute('data-email');
        const customerMessage = button.getAttribute('data-message');

        // Gán dữ liệu vào modal
        document.getElementById('feedbackId').value = feedbackId;
        document.getElementById('customerName').value = customerName;
        document.getElementById('customerEmail').value = customerEmail;
        document.getElementById('customerMessage').value = customerMessage;
    });
</script>
</body>
</html>
