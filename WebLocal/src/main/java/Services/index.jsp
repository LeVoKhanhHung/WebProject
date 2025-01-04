<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Product.ListProduct" %>
<%@ page import="Models.Category.Category" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 13/12/2024
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sản Phẩm</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!--    <link rel="stylesheet" href="css/bootstrap.min.css">-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<style>
    /* Khung sản phẩm */
    .product-item {
        border: 1px solid #ddd;
        transition: transform 0.3s ease, box-shadow 0.3s ease, background-color 0.3s ease;
        border-radius: 12px;
        overflow: hidden;
        position: relative;
        background: #fff;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    }

    .product-item:hover {
        transform: translateY(-5px);
        box-shadow: 0 12px 20px rgba(0, 0, 0, 0.2);
    }

    /* Hình ảnh sản phẩm */
    .img_product {
        position: relative;
        width: 100%;
        height: auto;
        overflow: hidden;
    }

    .default-img {
        width: 100%;
        height: auto;
        object-fit: cover;
        display: block;
        transition: opacity 0.3s ease-in-out;
    }

    .hover-img {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: auto;
        object-fit: cover;
        opacity: 0;
        transition: opacity 0.3s ease-in-out;
    }

    .img_product:hover .default-img {
        opacity: 0;
    }

    .img_product:hover .hover-img {
        opacity: 1;
    }

    /* Vùng hiển thị icon */
    .product-icons {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        display: flex;
        gap: 20px;
        opacity: 0;
        visibility: hidden;
        transition: opacity 0.3s ease-in-out, visibility 0.3s ease-in-out;
        z-index: 10;
    }

    .img_product:hover .product-icons {
        opacity: 1;
        visibility: visible;
    }

    /* Icon */
    .product-icons i {
        font-size: 22px;
        color: white;
        background: rgba(0, 0, 0, 0.6);
        padding: 12px;
        border-radius: 50%;
        cursor: pointer;
        transition: transform 0.3s ease, background-color 0.3s ease;
    }

    /* Hiệu ứng khi hover icon */
    .cart-icon:hover {
        transform: scale(1.2);
        background-color: #28a745;
    }

    .heart-icon:hover {
        transform: scale(1.2);
        background-color: #e74c3c;
    }

    /* Nút trọng lượng */
    .product_infor button {
        font-size: 0.9rem;
        padding: 6px 12px;
        cursor: pointer;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .product_infor button:hover {
        background-color: #f8f9fa;
        border-color: #007bff;
        color: #007bff;
    }

    /* Tên sản phẩm */
    .product_name {
        font-size: 1.1rem;
        font-weight: bold;
    }

    /* Giá */
    .price {
        font-size: 1.3rem;
        color: #dc3545;
    }
    /* Kiểu dáng thông báo */
    .notification {
        position: fixed;
        top: -100px; /* Đặt ban đầu thông báo nằm ngoài màn hình */
        right: 10px;
        background-color: rgba(0, 0, 0, 0.8);
        color: white;
        padding: 15px;
        border-radius: 8px;
        max-width: 300px;
        width: auto;
        z-index: 9999;
        opacity: 0; /* Ban đầu thông báo trong suốt */
        transition: opacity 0.5s ease-in-out, top 0.5s ease-out; /* Hiệu ứng mờ dần và di chuyển từ trên xuống */
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3); /* Tạo hiệu ứng bóng cho thông báo */
        font-family: Arial, sans-serif;
        font-size: 14px;
        line-height: 1.4;
    }

    .notification.show {
        opacity: 1; /* Hiển thị với độ mờ dần */
        top: 10px; /* Di chuyển vào vị trí hiển thị */
    }

    .notification .product-info {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .notification .product-info .product-name {
        font-weight: bold;
        margin-bottom: 5px;
    }

    .notification .product-info .product-price {
        color: #ff6f61;
        margin-bottom: 5px;
    }

    .notification .product-info .product-quantity {
        color: #fff;
        font-size: 12px;
    }
    .img_product img {
        width: 450px;
        height: 450px;
        /*width: 100%; !* Chiều rộng chiếm toàn bộ container *!*/
        /*height: 200px; !* Chiều cao cố định *!*/
        object-fit: cover; /* Cắt ảnh để phù hợp với kích thước container */
        border-bottom: 1px solid #eee; /* Đường viền dưới */
        border-radius: 5px;
    }

    /* Ẩn ảnh hover mặc định */
    /*.img_product .hover-img {*/
    /*    opacity: 0;*/
    /*    object-fit: cover;*/
    /*}*/

    /*!* Hiển thị ảnh hover khi di chuột *!*/
    /*.img_product:hover .hover-img {*/
    /*    opacity: 1;*/
    /*}*/

    /*.img_product:hover .default-img {*/
    /*    opacity: 0;*/
    /*}*/

</style>
<body>
<%@include file="header.jsp"%>
<section style="background-color: rgb(254,235,205)">
  <div class="row">
    <div class="d-flex justify-content-center">
        <div class="main_name fw-bold text-danger text-center fs-1 ">Sản Phẩm</div>
    </div>
   </div>
   <div class="row">
    <div class="col p-3 text-black">
        <div class="d-flex justify-content-center">
            <ul class="nav">
                <li class="nav-item">
                    <div class="">
                        <a class="nav-link fw-bold text-dark pb-0" href="#">NẤM QUÀ TẶNG</a>
                        <a class="nav-link fw-medium text-secondary pt-0" href="#">2 Sản Phẩm</a>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="">
                        <a class="nav-link fw-bold text-dark pb-0" href="#">BỘT NẤM ĂN</a>
                        <a class="nav-link fw-medium text-secondary pt-0" href="#">4 Sản Phẩm</a>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="">
                        <a class="nav-link fw-bold text-dark pb-0" href="#">NẤM DƯỢC LIỆU</a>
                        <a class="nav-link fw-medium text-secondary pt-0" href="#">7 Sản Phẩm</a>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="">
                        <a class="nav-link fw-bold text-dark pb-0" href="#">CHÀ BÔNG NẤM</a>
                        <a class="nav-link fw-medium text-secondary pt-0" href="#">3 Sản Phẩm</a>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="">
                        <a class="nav-link fw-bold text-dark pb-0" href="#">NẤM TƯƠI</a>
                        <a class="nav-link fw-medium text-secondary pt-0" href="#">21 Sản Phẩm</a>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="">
                        <a class="nav-link fw-bold text-dark pb-0" href="#">NẤM KHÔ</a>
                        <a class="nav-link fw-medium text-secondary pt-0" href="#">15 Sản Phẩm</a>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="">
                        <a class="nav-link fw-bold text-dark pb-0" href="#">PHÔI NẤM</a>
                        <a class="nav-link fw-medium text-secondary pt-0" href="#">1 Sản Phẩm</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
 </div>
</section>
<section class="menu">
    <div class="container">
        <div class="row">
            <div class="col-3 text-danger fw-bold fs-5 mt-5" style="--bs-text-opacity: .85;">
                <div class="d-flex ps-1">
                    MENU SẢN PHẨM
                </div>
            </div>
            <div class="col-3 mt-5">
                <div class="d-flex justify-content-center">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a class="text-secondary" href="#">Trang Chủ</a></li>
                            <li class="breadcrumb-item"><a class="text-secondary" href="#">Sản Phẩm</a></li>
                            <li class="breadcrumb-item" aria-current="page">Data</li>
                        </ol>
                    </nav>
                </div>
            </div>
            <div class="col-3 mt-5">
                <div class="d-flex justify-content-center">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="col-3 mt-5">
                <div class="d-flex justify-content-center">
                    <button type="button" class="btn dropdown-toggle fw-bold" data-bs-toggle="dropdown">Thứ tự theo mức độ phổ biến</button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Thứ tự theo mức độ phổ biến</a></li>
                        <li><a class="dropdown-item" href="#">Thứ tự theo điểm đánh giá</a></li>
                        <li><a class="dropdown-item" href="#">Mới nhất</a></li>
                        <li><a class="dropdown-item" href="#">Thứ tự theo giá: thấp đến cao</a></li>
                        <li><a class="dropdown-item" href="#">Thứ tự theo giá: cao đến thấp</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="content">
    <%
    ListProduct item = (ListProduct) session.getAttribute("listproduct");
    if(item == null){
        item = new ListProduct();
        session.setAttribute("listproduct",item);
    }

%>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="d-flex ps-1">
                    <div class="vstack gap-3">
                        <div class="row">
                            <div class="col">
                                <ul class="nav pb-0 text-secondary fw-medium">
                                    <c:forEach var="item" items="${sessionScope.categories.items}" >
                                        <li class="">${item.name}</li>
                                    </c:forEach>

                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Chà Bông Nấm</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Nấm Dược Liệu</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Nấm Khô</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Nấm Quà Tặng</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Nấm Tươi</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Phôi Nấm</li>
                                </ul>
                            </div>
                            <div class="col-4 fs-6">
                                <ul class="nav pb-0 text-secondary fw-medium">
                                    <li class="ps-5"><div class="badge border text-bg-light">4</div></li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5"><div class="badge border text-bg-light">3</div></li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5"><div class="badge border text-bg-light">7</div></li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5"><div class="badge border text-bg-light ">15</div></li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5"><div class="badge border text-bg-light">2</div></li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5"><div class="badge border text-bg-light">21</div></li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5"><div class="badge border text-bg-light">1</div></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pt-2">
                    <label for="customRange3" class="form-label text-danger fw-bold fs-5">Khoảng Giá</label>
                    <input type="range" class="form-range" min="0" max="10.000" step="20.000.000" id="customRange3">
                    <div class="row ps-3">
                        <ul class="nav">
                            <li class="nav_item col-2 text-secondary d-flex justify-content-left">Giá:</li>
                            <li class="col-8 fw-bold">0đ - 20.000.000đ</li>
                            <li class="nav_item">
                                <button type="button" class="btn pt-0">Lọc</button>
                            </li>
                        </ul>
                    </div>
                </div>
                <div>
                    <div class="pt-3">
                        <div class="d-flex ps-1 text-danger fw-bold fs-5">Bán Chạy Nhất</div>
                    </div>
                    <div class="">
                        <ul class="nav mt-2">
                            <li class="h-80 d-inline-block pt-2" style="height:80px">
                                <a href=""><img src="img/img19.png" style="width: 80px" class="" alt="..."></a>
                            </li>
                            <li class="">
                                <a class="nav-link fw-medium text-dark pt-1" href="#">Nấm Hương Tươi</a>
                                <ul class="nav">
                                    <li class=""><div class="ms-3"><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                </ul>
                                <div class="ms-3">22.000đ - 85.000đ</div>
                            </li>
                        </ul>
                        <ul class="nav mt-3">
                            <li class="h-80 d-inline-bloc pt-2" style="height:80px">
                                <a href="#"><img src="img/img3.png" style="width: 80px" class="" alt="..."></a>
                            </li>
                            <li class="">
                                <a class="nav-link fw-medium text-dark pt-1" href="#">Nấm Mỡ Tươi</a>
                                <ul class="nav">
                                    <li class=""><div class="ms-3"><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                </ul>
                                <div class="ms-3">39.000đ - 165.000đ</div>
                            </li>
                        </ul>

                        <ul class="nav mt-3">
                            <!-- Phần hình ảnh -->
                            <li class="h-80 d-inline-block pt-2" style="height:80px">
                                <a href="#"><img src="img/img7.png" style="width: 80px" alt="Nấm Mỡ Nâu Tươi"></a>
                            </li>

                            <!-- Phần nội dung sản phẩm -->
                            <li>
                                <a class="nav-link fw-medium text-dark pt-1" href="#">Nấm Mỡ Nâu Tươi</a>
                                <ul class="nav">
                                    <li><div class="ms-3"><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li><div><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li><div><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li><div><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li><div><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                </ul>
                                <div class="ms-3">58.000đ - 250.000đ</div>


                            </li>
                        </ul>
                        <ul class="nav mt-3">
                            <li class="h-80 d-inline-block pt-2" style="height:80px">
                                <a href=""><img src="img/img11.png" style="width: 80px" class="" alt="..."></a>
                            </li>
                            <li class="">
                                <a class="nav-link fw-medium text-dark pt-1" href="">Nấm Rôm Sấy</a>
                                <ul class="nav">
                                    <li class=""><div class="ms-3"><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                    <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                                </ul>
                                <div class="ms-3">39.000đ - 185.000đ</div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-9" style="">
                <div class="d-flex ps-5" style="width: 100%;">


                    <ul class="nav d-flex flex-wrap gap-4" style="width: 100%">
                        <c:forEach var="item" items="${sessionScope.listproduct.items}">
                            <li class="nav-item" style="width: 30%; height: 500px">
                                <div class="h-100 d-inline-block border rounded product-item shadow-sm" style="height: auto; position: relative; overflow: hidden; background: #fff; border-radius: 10px;">
                                    <!-- Hình ảnh sản phẩm -->
                                    <div class="img_product position-relative">
                                        <a href="product_detail?id=${item.id}" style="text-decoration: none; color: inherit;">
                                            <img src="img/${item.fileName1}" class="default-img" alt="Product Image" style="width: 100%; height: 300px; border-bottom: 1px solid #eee;">
                                            <img src="img/${item.fileName2}" class="hover-img position-absolute" alt="Hover Image" style="width: 100%; height: auto; top: 0; left: 0; transition: opacity 0.3s ease;">
                                        </a>
                                        <!-- Icons hiển thị khi hover -->
                                        <div class="product-icons position-absolute d-flex gap-3 justify-content-center align-items-center" style="top: 50%; left: 50%; transform: translate(-50%, -50%); transition: opacity 0.5s;">
                                            <!-- Icon giỏ hàng -->
                                            <form id="cart-form-${item.id}" action="product_deIcon" method="GET" style="display: none;">
                                                <input type="hidden" name="productID" value="${item.id}">
                                                <input type="hidden" name="weight" class="selected-weight" value="">
                                            </form>

                                            <!-- Icon giỏ hàng -->
                                            <a href="#"
                                               class="fas fa-shopping-cart cart-icon"
                                               style="font-size: 20px; background: rgba(0,0,0,0.6); color: white; padding: 10px; border-radius: 50%; text-decoration: none;"
                                               onclick="submitCartForm('${item.id}')">
                                            </a>
                                            <div id="notification1" class="alert alert-success" style="display: none; position: fixed; top: 20px; right: 20px; z-index: 1000;">
                                                Sản phẩm đã được thêm vào giỏ hàng thành công!
                                            </div>
                                            <!-- Icon yêu thích -->
                                            <a href="#"
                                               class="fas fa-heart heart-icon"
                                               style="font-size: 20px; background: rgba(0,0,0,0.6); color: white; padding: 10px; border-radius: 50%; text-decoration: none;"
                                               onclick="addToFavorites('${item.name}', '${item.fileName2}')">
                                            </a>

                                        </div>
                                    </div>
                                    <!-- Thông tin sản phẩm -->
                                    <div class="p-3 text-center">
                                        <div class="product_infor d-flex justify-content-center gap-2 mb-2">
                                            <button data-id="${item.id}" data-weight="200" class="btn btn-sm btn-outline-secondary rounded-pill weight-btn">200g</button>
                                            <button data-id="${item.id}" data-weight="500" class="btn btn-sm btn-outline-secondary rounded-pill weight-btn">500g</button>
                                            <button data-id="${item.id}" data-weight="1000" class="btn btn-sm btn-outline-secondary rounded-pill weight-btn">1kg</button>
                                        </div>
                                        <div class="product_name text-dark fw-bold mb-2">${item.name}</div>
                                        <div class="price text-danger fw-bold" style="font-size: 1.2rem;">${item.priceMin}đ - ${item.priceMax}đ</div>
                                    </div>
                                </div>
                            </li>

                            <div id="notification" class="notification" style="display:block; width: 40%; border-radius: 10px">
                                <img id="notification-img" src="img/${item.fileName2}" alt="Product Image" style="width: 50%; border-radius: 5px">
                                <div id="notification-name"></div>
                                <div id="notification-price"></div>
                                <div id="notification-quantity"></div>
                                <div id="notification-message" class="message">Thành công</div>
                            </div>
                        </c:forEach>

                    </ul>


                </div>
                <div class="col-17 mt-5">
                    <div class="d-flex justify-content-center">
                        <nav aria-label="Page navigation example">
                            <div class="pagination">
                                <c:if test="${sessionScope.currentPage > 1}">
                                    <a href="product_category?idCategory=${param.idCategory}&page=${sessionScope.currentPage - 1}" class="btn btn-primary">Previous</a>
                                </c:if>

                                <c:forEach begin="1" end="${sessionScope.totalPages}" var="i">
                                    <a href="product_category?idCategory=${param.idCategory}&page=${i}" class="btn ${i == sessionScope.currentPage ? 'btn-secondary' : 'btn-outline-secondary'}">
                                            ${i}
                                    </a>
                                </c:forEach>

                                <c:if test="${sessionScope.currentPage < sessionScope.totalPages}">
                                    <a href="product_category?idCategory=${param.idCategory}&page=${sessionScope.currentPage + 1}" class="btn btn-primary">Next</a>
                                </c:if>
                            </div>
                        </nav>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="footer.jsp"%>
<script>
    /* JavaScript cho nút active */
    document.querySelectorAll('.product_infor button').forEach(function(button) {
        button.addEventListener('click', function() {
            // Xóa class active khỏi tất cả các nút
            document.querySelectorAll('.product_infor button').forEach(function(btn) {
                btn.classList.remove('active');
            });
            // Thêm class active cho nút được chọn
            this.classList.add('active');
        });
    });

    function addToCart(productName, productPrice, img) {
        showNotification(productName, productPrice, 'cart', img);
    }

    function addToFavorites(productName, img) {
        showNotification(productName, '', 'favorite', img);
    }

    function showNotification(productName, productPrice, type, img) {
        var notification = document.getElementById('notification');
        var notificationImg = document.getElementById('notification-img');
        var notificationName = document.getElementById('notification-name');
        var notificationPrice = document.getElementById('notification-price');
        var notificationQuantity = document.getElementById('notification-quantity');

        // Cập nhật thông tin cho notification
        notificationName.innerText = productName;
        if (productPrice) {
            notificationPrice.innerText = productPrice.toLocaleString() + 'đ';
            notificationQuantity.innerText = 'Số lượng: 1';
        } else {
            notificationPrice.innerText = '';
            notificationQuantity.innerText = '';
        }

        // Hiển thị hình ảnh sản phẩm
        notificationImg.src = 'img/' + img;

        // Hiển thị thông báo ngay lập tức
        notification.style.display = 'block';
        notification.classList.add('show');

        // Ẩn dần thông báo sau 3 giây
        setTimeout(function() {
            notification.classList.remove('show');
        }, 3000);

        // Sau khi ẩn dần, ẩn hoàn toàn
        setTimeout(function() {
            notification.style.display = 'none';
        }, 3500);
    }

    // Object lưu các trọng lượng đã chọn
    var selectedWeights = {};

    // Lắng nghe sự kiện chọn trọng lượng
    document.querySelectorAll('.weight-btn').forEach(function(button) {
        button.addEventListener('click', function() {
            var productId = this.getAttribute('data-id');
            var weight = this.getAttribute('data-weight');

            // Lưu trọng lượng đã chọn vào object
            selectedWeights[productId] = weight;

            // Cập nhật class 'active-weight' cho nút được chọn
            document.querySelectorAll("[data-id='" + productId + "']").forEach(function(btn) {
                btn.classList.remove('active-weight');
            });
            this.classList.add('active-weight');

            // Cập nhật trọng lượng vào form ẩn
            var form = document.getElementById('cart-form-' + productId);
            var weightInput = form.querySelector('.selected-weight');
            weightInput.value = weight;
        });
    });

    // Gửi form khi nhấn vào icon giỏ hàng
    function submitCartForm(productId) {
        var form = document.getElementById('cart-form-' + productId);

        // Kiểm tra nếu chưa chọn trọng lượng
        if (!form.querySelector('.selected-weight').value) {
            alert('Vui lòng chọn trọng lượng trước khi thêm vào giỏ hàng!');
            return;
        }

        // Gửi form đến servlet
        form.submit();
    }

</script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/updateProductPrice.js"></script>
</body>
</html>