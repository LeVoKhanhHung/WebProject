<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Models.Supplier.Supplier" %>
<%@ page import="Models.Category.Category" %><%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 15/12/2024
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/admin-manage-product.css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar">
            <div class="position-sticky">
                <h3 class="text-center my-3">Admin Panel</h3>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="index.html">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="manage-product.html">Quản lý sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-promotion.html">Quản lý chương trình khuyến mãi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-review.html">Quản lý đánh giá sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-order.html">Quản lý đơn hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="statistics.html">Thống kê và báo cáo doanh thu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manage-user.html">Quản lý người dùng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="feedback.html">Phản hồi khách hàng</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Main content -->
        <div class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Quản lý sản phẩm</h1>
            </div>
            <div class="main-content">
                <table class="table table-striped table-bordered">
                    <div class="d-flex justify-content-end">
                        <button class="btn btn-sm btn-success btn-lg" data-bs-toggle="modal" data-bs-target="#addModal">Thêm Sản Phẩm</button>
                    </div>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên sản phẩm</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Link ảnh</th>
                        <th>Mô tả</th>
                        <th>Nhà cung cấp</th>
                        <th>Phân loại</th>
                        <th>Trạng thái hoạt động</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>Nấm Linh Chi</td>
                        <td>300,000đ</td>
                        <td>15</td>
                        <td><img src="../img/img44.png" alt="Nấm Linh Chi" width="50"></td>
                        <td class="short-description">Mô tả sản phẩm về nấm Linh Chi</td>
                        <td>Nhà cung cấp A</td>
                        <td>Tươi</td>
                        <td>Còn hàng</td>
                        <td>
                            <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">Sửa</button>
                            <button class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Xóa</button>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Nấm Cordyceps</td>
                        <td>500,000đ</td>
                        <td>10</td>
                        <td><img src="../img/img44.png" alt="Nấm Cordyceps" width="50"></td>
                        <td class="short-description">Mô tả sản phẩm về nấm Cordyceps</td>
                        <td>Nhà cung cấp B</td>
                        <td>Khô</td>
                        <td>Hết hàng</td>
                        <td>
                            <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">Sửa</button>
                            <button class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Xóa</button>
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Nấm Hương</td>
                        <td>150,000đ</td>
                        <td>30</td>
                        <td><img src="../img/img44.png" alt="Nấm Hương" width="50"></td>
                        <td class="short-description">Mô tả sản phẩm về nấm Hương</td>
                        <td>Nhà cung cấp C</td>
                        <td>Bột</td>
                        <td>Còn hàng</td>
                        <td>
                            <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">Sửa</button>
                            <button class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Xóa</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- Modal Thêm sản phẩm-->
<div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Thêm sản phẩm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="../WebFinall/addProduct" method="POST" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="productName" class="form-label">Tên sản phẩm</label>
                        <input type="text" class="form-control" id="product_Name" name="product_Name">
                    </div>
                    <div class="mb-3">
                        <label for="productPrice" class="form-label">Giá</label>
                        <input type="text" class="form-control" id="product_Price" name="product_Price">
                    </div>
                    <div class="mb-3">
                        <label for="productQuantity" class="form-label">Số lượng</label>
                        <input type="number" class="form-control" id="product_Quantity" name="product_Quantity">
                    </div>
                    <div class="mb-3">
                        <label for="productWeight" class="form-label">Trọng lượng</label>
                        <select class="form-control" id="productWeight" name="product_Weight">
                            <option value="200">200gram</option>
                            <option value="500">500gram</option>
                            <option value="1000">1kg</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="productImages" class="form-label">Ảnh sản phẩm 1</label>
                        <input type="file" class="form-control" id="productImages" name="product_Image1" multiple>
                    </div>
                    <div class="mb-3">
                        <label for="productImages" class="form-label">Ảnh sản phẩm 2</label>
                        <input type="file" class="form-control" id="productImages1" name="product_Image2" multiple>
                    </div>
                    <div class="mb-3">
                        <label for="productImages" class="form-label">Ảnh sản phẩm 3</label>
                        <input type="file" class="form-control" id="productImages2" name="product_Image3" multiple>
                    </div>
                    <div class="mb-3">
                        <label for="productImages" class="form-label">Ảnh sản phẩm 4</label>
                        <input type="file" class="form-control" id="productImages3" name="product_Image4" multiple>
                    </div>
                    <div class="mb-3">
                        <label for="productDescription" class="form-label">Mô tả</label>
                        <textarea class="form-control" id="product_Description" name="product_Description" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <%
                            Supplier item = (Supplier) session.getAttribute("suppliers");
                            if(item == null){
                                item = new Supplier();
                                session.setAttribute("suppliers",item);
                            }
                            Category item1 = (Category) session.getAttribute("categories");
                            if(item1 == null){
                                item1 = new Category();
                                session.setAttribute("categories",item1);
                            }
                            System.out.println(item.getItems().size());
                        %>
                        <label for="productSupplier" class="form-label">Nhà cung cấp</label>
                        <select class="form-control" id="product_Supplier" name="product_Supplier">
                            <c:forEach var="item" items="${sessionScope.suppliers.items}">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>

                    </div>
                    <div class="mb-3">
                        <label for="importDate" class="form-label">Ngày thêm sản phẩm</label>
                        <input type="date" class="form-control" id="importDate" name="importDate">
                    </div>
                    <div class="mb-3">
                        <label for="productCategory" class="form-label">Phân loại</label>
                        <select class="form-select" id="product_Category" name="product_Category">
                            <c:forEach var="item" items="${sessionScope.categories.items}">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>


                        </select>
                    </div>
                    <div id="promotionSection">
                        <h5>Khuyến mãi</h5>
                        <label for="salePercent" class="form-label">Giảm giá (%)</label>
                        <input type="number" class="form-control" id="salePercent" name="sale_Percent" min="0" max="100" placeholder="Nhập % giảm giá">
                        <label for="saleStartDate" class="form-label">Ngày bắt đầu</label>
                        <input type="date" class="form-control" id="saleStartDate" name="sale_StartDate">
                        <label for="saleEndDate" class="form-label">Ngày kết thúc</label>
                        <input type="date" class="form-control" id="saleEndDate" name="sale_EndDate">
                    </div>
                    <div class="mb-3">
                        <label for="productStatus" class="form-label">Trạng thái hoạt động</label>
                        <select class="form-select" id="product_Status" name="product_Status">
                            <option value="Còn hàng">Còn hàng</option>
                            <option value="Hết hàng">Hết hàng</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Áp dụng</button>
                </form>

            </div>
        </div>
    </div>
</div>

<!-- Modal Sửa -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Chỉnh sửa sản phẩm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <label for="productName" class="form-label">Tên sản phẩm</label>
                        <input type="text" class="form-control" id="productName">
                    </div>
                    <div class="mb-3">
                        <label for="productPrice" class="form-label">Giá</label>
                        <input type="text" class="form-control" id="productPrice">
                    </div>
                    <div class="mb-3">
                        <label for="productQuantity" class="form-label">Số lượng</label>
                        <input type="number" class="form-control" id="productQuantity">
                    </div>

                    <div class="mb-3">
                        <label for="productDescription" class="form-label">Mô tả</label>
                        <textarea class="form-control" id="productDescription" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="productSupplier" class="form-label">Nhà cung cấp</label>
                        <input type="text" class="form-control" id="productSupplier">
                    </div>
                    <div class="mb-3">
                        <label for="productCategory" class="form-label">Phân loại</label>
                        <select class="form-select" id="productCategory">
                            <option value="Tươi">Tươi</option>
                            <option value="Khô">Khô</option>
                            <option value="Bột">Bột</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="productStatus" class="form-label">Trạng thái hoạt động</label>
                        <select class="form-select" id="productStatus">
                            <option value="Còn hàng">Còn hàng</option>
                            <option value="Hết hàng">Hết hàng</option>
                            <option value="Ngưng bán">Ngưng bán</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- Modal Xóa -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Xóa sản phẩm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn muốn xóa sản phẩm này?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <button type="button" class="btn btn-danger">Xóa</button>
            </div>
        </div>
    </div>
</div>

<script src="js/manage-product.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
