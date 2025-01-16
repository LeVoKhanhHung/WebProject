<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý chương trình khuyến mãi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/admin-index.css' />">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <% request.setAttribute("activePage", "manage-promotion"); %>
        <jsp:include page="sidebar.jsp" />

        <!-- Main content -->
        <div class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Quản lý chương trình khuyến mãi</h1>
            </div>

            <div class="overview">
                <h2>Danh sách chương trình khuyến mãi</h2>
                <p>Admin có thể thêm, sửa hoặc xóa các chương trình khuyến mãi tại đây.</p>
                <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addPromotionModal">Thêm chương trình khuyến mãi</button>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>ID Biến thể</th>
                        <th>Giảm giá (%)</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="promotion" items="${promotionList}">
                        <tr>
                            <td>${promotion.id}</td>
                            <td>${promotion.idVariant}</td>
                            <td>${promotion.salePercent}%</td>
                            <td>${promotion.saleStartDate}</td>
                            <td>${promotion.saleEndDate}</td>
                            <td>
                                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editPromotionModal" data-id="${promotion.id}" data-idvariant="${promotion.idVariant}" data-salepercent="${promotion.salePercent}" data-startdate="${promotion.saleStartDate}" data-enddate="${promotion.saleEndDate}">Sửa</button>
                                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deletePromotionModal" data-id="${promotion.id}">Xóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Modal thêm chương trình khuyến mãi -->
<div class="modal" id="addPromotionModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm chương trình khuyến mãi</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/admin/addPromotion" method="post">
                    <div class="mb-3">
                        <label for="idVariant" class="form-label">ID Biến thể</label>
                        <input type="text" class="form-control" id="idVariant" name="idVariant" required>
                    </div>
                    <div class="mb-3">
                        <label for="salePercent" class="form-label">Giảm giá (%)</label>
                        <input type="number" class="form-control" id="salePercent" name="salePercent" required>
                    </div>
                    <div class="mb-3">
                        <label for="startDate" class="form-label">Ngày bắt đầu</label>
                        <input type="date" class="form-control" id="startDate" name="saleStartDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="endDate" class="form-label">Ngày kết thúc</label>
                        <input type="date" class="form-control" id="endDate" name="saleEndDate" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Thêm</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal Sửa Chương Trình Khuyến Mãi -->
<div class="modal fade" id="editPromotionModal" tabindex="-1" aria-labelledby="editPromotionModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editPromotionModalLabel">Sửa chương trình khuyến mãi</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/admin/editPromotion" method="post">
                    <input type="hidden" id="editPromotionId" name="promotionId">
                    <div class="mb-3">
                        <label for="editIdVariant" class="form-label">ID Biến thể</label>
                        <input type="text" class="form-control" id="editIdVariant" name="idVariant" required>
                    </div>
                    <div class="mb-3">
                        <label for="editSalePercent" class="form-label">Giảm giá (%)</label>
                        <input type="number" class="form-control" id="editSalePercent" name="salePercent" required>
                    </div>
                    <div class="mb-3">
                        <label for="editStartDate" class="form-label">Ngày bắt đầu</label>
                        <input type="date" class="form-control" id="editStartDate" name="saleStartDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="editEndDate" class="form-label">Ngày kết thúc</label>
                        <input type="date" class="form-control" id="editEndDate" name="saleEndDate" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Cập nhật chương trình</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal Xóa Chương Trình Khuyến Mãi -->
<div class="modal fade" id="deletePromotionModal" tabindex="-1" aria-labelledby="deletePromotionModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deletePromotionModalLabel">Xóa chương trình khuyến mãi</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/admin/deletePromotion" method="post">
                    <input type="hidden" id="deletePromotionId" name="promotionId">
                    <p>Bạn có chắc chắn muốn xóa chương trình khuyến mãi này?</p>
                    <button type="submit" class="btn btn-danger">Xóa</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // JavaScript xử lý truyền dữ liệu cho các modal thêm, sửa và xóa
    document.querySelector('#addPromotionModal').addEventListener('show.bs.modal', function (event) {
        // Reset các giá trị trong modal khi mở
        document.getElementById('addPromotionForm').reset();
    });

    document.querySelector('#editPromotionModal').addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;
        document.getElementById('editPromotionId').value = button.getAttribute('data-id');
        document.getElementById('editIdVariant').value = button.getAttribute('data-idvariant');
        document.getElementById('editSalePercent').value = button.getAttribute('data-salepercent');
        document.getElementById('editStartDate').value = button.getAttribute('data-startdate');
        document.getElementById('editEndDate').value = button.getAttribute('data-enddate');
    });

    document.querySelector('#deletePromotionModal').addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;
        document.getElementById('deletePromotionId').value = button.getAttribute('data-id');
    });
</script>

</body>
</html>
