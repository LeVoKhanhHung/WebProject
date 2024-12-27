<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sản Phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
</head>
<body>
<!-- Header -->
<jsp:include page="header.jsp"/>

<!-- Menu-->
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
                            <li class="breadcrumb-item"><a class="text-secondary" href="/index.jsp">Trang Chủ</a></li>
                            <li class="breadcrumb-item"><a class="text-secondary" href="/product.jsp">Sản Phẩm</a></li>
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
                    <button type="button" class="btn dropdown-toggle fw-bold" data-bs-toggle="dropdown">Thứ tự theo mức độ phổ 					biến</button>
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

<!-- Content -->
<section class="content">
    <div class="container">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-3">
                <div class="d-flex ps-1">
                    <div class="vstack gap-3">
                        <div class="row">
                            <div class="col">
                                <ul class="nav pb-0 text-secondary fw-medium">
                                    <li class="">Nấm Tươi</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Nấm Khô</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Bột Nấm Ăn</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Nấm Dược Liệu</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Chà Bông Nấm</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Phôi Nấm</li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="">Nấm Quà Tặng</li>
                                </ul>
                            </div>
                            <div class="col-4 fs-6">
                                <ul class="nav pb-0 text-secondary fw-medium">
                                    <li class="ps-5">
                                        <div class="badge border text-bg-light">21</div>
                                    </li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5">
                                        <div class="badge border text-bg-light">15</div>
                                    </li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5">
                                        <div class="badge border text-bg-light">4</div>
                                    </li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5">
                                        <div class="badge border text-bg-light ">7</div>
                                    </li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5">
                                        <div class="badge border text-bg-light">3</div>
                                    </li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5">
                                        <div class="badge border text-bg-light">1</div>
                                    </li>
                                </ul>
                                <ul class="nav pt-2 text-secondary fw-medium">
                                    <li class="ps-5">
                                        <div class="badge border text-bg-light">2</div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pt-2">
                    <label class="form-label text-danger fw-bold fs-5">Khoảng Giá</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="priceRange" id="priceRange1"
                               value="0-1000000">
                        <label class="form-check-label" for="priceRange1">0đ - 1.000.000đ</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="priceRange" id="priceRange2"
                               value="1000000-5000000">
                        <label class="form-check-label" for="priceRange2">1.000.000đ - 5.000.000đ</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="priceRange" id="priceRange3"
                               value="5000000-10000000">
                        <label class="form-check-label" for="priceRange3">5.000.000đ - 10.000.000đ</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="priceRange" id="priceRange4"
                               value="10000000-20000000">
                        <label class="form-check-label" for="priceRange4">10.000.000đ - 20.000.000đ</label>
                    </div>
                    <div class="pt-2">
                        <button type="button" class="btn btn-primary">Lọc</button>
                    </div>
                </div>
                <div>
                    <div class="pt-3">
                        <div class="d-flex ps-1 text-danger fw-bold fs-5">Bán Chạy Nhất</div>
                    </div>
                    <div class="">
                        <ul class="nav mt-2">
                            <li class="h-80 d-inline-block pt-2" style="height:80px">
                                <a href="product-detail.jsp"><img src="/img/img19.png" style="width: 80px" class="" alt="..."></a>
                            </li>
                            <li class="">
                                <a class="nav-link fw-medium text-dark pt-1" href="product-detail.jsp">Nấm Hương Tươi</a>
                                <div class="stars"></div>
                                <div class="ms-3">22.000đ - 85.000đ</div>
                            </li>
                        </ul>
                        <ul class="nav mt-3">
                            <li class="h-80 d-inline-bloc pt-2" style="height:80px">
                                <a href="#"><img src="/img/img3.png" style="width: 80px" class="" alt="..."></a>
                            </li>
                            <li class="">
                                <a class="nav-link fw-medium text-dark pt-1" href="#">Nấm Mỡ Trắng Tươi</a>
                                <div class="stars"></div>
                                <div class="ms-3">39.000đ - 165.000đ</div>
                            </li>
                        </ul>
                        <ul class="nav mt-3">
                            <li class="h-80 d-inline-block pt-2" style="height:80px">
                                <a href="#"><img src="/img/img12.png" style="width: 80px" class="" alt="..."></a>
                            </li>
                            <li class="">
                                <a class="nav-link fw-medium text-dark pt-1" href="">Bột Linh Chi Đỏ Thái</a>
                                <div class="stars"></div>
                                <div class="ms-3">65.000đ - 1.050.000đ</div>
                            </li>
                        </ul>
                        <ul class="nav mt-3">
                            <li class="h-80 d-inline-block pt-2" style="height:80px">
                                <a href=""><img src="/img/img7.png" style="width: 80px" class="" alt="..."></a>
                            </li>
                            <li class="">
                                <a class="nav-link fw-medium text-dark pt-1" href="">Nấm Mỡ Nâu Tươi</a>
                                <div class="stars"></div>
                                <div class="ms-3">58.000đ - 250.000đ</div>
                            </li>
                        </ul>
                        <ul class="nav mt-3">
                            <li class="h-80 d-inline-block pt-2" style="height:80px">
                                <a href=""><img src="/img/img11.png" style="width: 80px" class="" alt="..."></a>
                            </li>
                            <li class="">
                                <a class="nav-link fw-medium text-dark pt-1" href="">Nấm Rơm Sấy</a>
                                <div class="stars"></div>
                                <div class="ms-3">39.000đ - 185.000đ</div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <!-- Main Content -->
            <div class="col-9">
                <!-- List of products dynamically loaded here -->
                <ul class="nav product-list">
                    <%-- Example Product Item --%>
                    <li class="nav-item product-item">
                        <div class="h-100 d-inline-block border rounded" style="height:100px">
                            <div class="container">
                                <div class="img_product">
                                    <img src="/img/img1.png" style="width: 200px" class="" alt="...">
                                </div>
                            </div>
                            <div class="product_infor d-flex justify-content-center">
                                <button data-weight="200" class="btn">200g</button>
                                <button data-weight="500" class="btn">500g</button>
                                <button data-weight="1000" class="btn">1kg</button>
                            </div>
                            <div class="product_name d-flex justify-content-center">Nấm Hương Tươi</div>
                            <div class="mt-2 d-flex justify-content-center fw-bold text-danger"
                                 style="--bs-text-opacity: 0.8;">58.000đ - 250.000đ
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<jsp:include page="footer.jsp"/>

</body>
</html>
