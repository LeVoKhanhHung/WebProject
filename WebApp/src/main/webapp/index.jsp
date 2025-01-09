<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body>
<!-- Header -->
<jsp:include page="header.jsp"/>

<!-- Banner Quảng Cáo -->
<section class="banner text-white text-center py-5" style="background-color: #CD853F;">
    <div class="container">
        <h2 class="display-4">Khám Phá Những Sản Phẩm Mới Nhất!</h2>
        <p class="lead">Chọn lựa từ các loại nấm tự nhiên, tốt cho sức khỏe.</p>
    </div>
</section>

<!-- Giới Thiệu Sản Phẩm Bán Chạy -->
<section class="best-sellers py-5">
    <div class="container text-center">
        <h3 class="mb-4 text-uppercase text-dark">Sản Phẩm Bán Chạy</h3>
        <div class="row g-4">
            <!-- Dùng JSTL để lặp qua danh sách sản phẩm -->
            <c:forEach var="product" items="${bestSellers}">
                <div class="col-md-4">
                    <div class="product-item p-3 border rounded shadow-sm">
                        <img src="${product.image}" alt="${product.name}" class="img-fluid rounded mb-3">
                        <p class="fw-bold">${product.name}</p>
                        <p class="text-muted">${product.price}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- Giới Thiệu Sản Phẩm Mới -->
<section class="new-arrivals py-5 bg-light">
    <div class="container text-center">
        <h3 class="mb-4 text-uppercase text-dark">Sản Phẩm Mới</h3>
        <div class="row g-4">
            <!-- Dùng JSTL để lặp qua danh sách sản phẩm mới -->
            <c:forEach var="product" items="${newArrivals}">
                <div class="col-md-4">
                    <div class="product-item p-3 border rounded shadow-sm">
                        <img src="${product.image}" alt="${product.name}" class="img-fluid rounded mb-3">
                        <p class="fw-bold">${product.name}</p>
                        <p class="text-muted">${product.price}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- Footer -->
<jsp:include page="footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
