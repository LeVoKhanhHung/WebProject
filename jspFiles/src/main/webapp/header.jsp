<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="header">
  <div>
    <div style="background-color: antiquewhite">
      <div class="row">
        <!-- Logo -->
        <div class="col-3 p-2 text-black">
          <div class="d-flex justify-content-center">
            <img src="<%= request.getContextPath() %>/img/logo.png" style="height: 50px" alt="Logo">
          </div>
        </div>
        <!-- Navigation Menu -->
        <div class="col-6 p-3 text-black">
          <div class="d-flex justify-content-center">
            <ul class="nav">
              <li class="nav-item">
                <a class="nav-link fw-bold text-dark" href="index.jsp">HOME</a>
              </li>
              <li class="nav-item">
                <button type="button" class="btn dropdown-toggle fw-bold" data-bs-toggle="dropdown">SẢN PHẨM</button>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="product.jsp">Nấm Tươi</a></li>
                  <li><a class="dropdown-item" href="#">Nấm Khô</a></li>
                  <li><a class="dropdown-item" href="#">Bột Nấm Ăn</a></li>
                  <li><a class="dropdown-item" href="#">Nấm Dược Liệu</a></li>
                  <li><a class="dropdown-item" href="#">Chà Bông Nấm</a></li>
                  <li><a class="dropdown-item" href="#">Phôi Nấm</a></li>
                  <li><a class="dropdown-item" href="#">Nấm Quà Tặng</a></li>
                </ul>
              </li>
              <li>
                <button type="button" class="btn dropdown-toggle fw-bold" data-bs-toggle="dropdown">CHIA SẺ</button>
              </li>
              <li class="nav-item">
                <a class="nav-link fw-bold text-dark" href="#">HỢP TÁC</a>
              </li>
              <li class="nav-item">
                <a class="nav-link fw-bold text-dark" href="about-us.jsp">CÔNG TY</a>
              </li>
            </ul>
          </div>
        </div>
        <!-- User, Search, Cart Icons -->
        <div class="col-3 p-3 text-black">
          <div class="d-flex justify-content-center">
            <ul class="nav">
              <li class="nav-item">
                <button type="button" class="btn btn-warning" id="user-name-btn">guest</button>
              </li>
              <li class="nav-item">
                <button type="button" class="btn">
                  <i class="fa-solid fa-magnifying-glass"></i>
                </button>
              </li>
              <li class="nav-item">
                <button type="button" class="btn" onclick="redirectToGuestInfo()">
                  <i class="fa-solid fa-user"></i>
                </button>
              </li>
              <li class="nav-item">
                <button type="button" class="btn" onclick="location.href='giohang.jsp';">
                  <i class="fa-solid fa-cart-shopping"></i>
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!-- Main Title -->
      <div class="row">
        <div class="d-flex justify-content-center">
          <div class="main_name fw-bold text-danger text-center fs-1">
            <a href="product.jsp" class="text-decoration-none text-danger">Sản Phẩm</a>
          </div>
        </div>
      </div>
      <!-- Categories -->
      <div class="row">
        <div class="col p-3 text-black">
          <div class="d-flex justify-content-center">
            <ul class="nav">
              <li class="nav-item">
                <div>
                  <a class="nav-link fw-bold text-dark pb-0" href="#">NẤM TƯƠI</a>
                  <a class="nav-link fw-medium text-secondary pt-0" href="#">21 Sản Phẩm</a>
                </div>
              </li>
              <li class="nav-item">
                <div>
                  <a class="nav-link fw-bold text-dark pb-0" href="#">NẤM KHÔ</a>
                  <a class="nav-link fw-medium text-secondary pt-0" href="#">15 Sản Phẩm</a>
                </div>
              </li>
              <li class="nav-item">
                <div>
                  <a class="nav-link fw-bold text-dark pb-0" href="#">BỘT NẤM ĂN</a>
                  <a class="nav-link fw-medium text-secondary pt-0" href="#">4 Sản Phẩm</a>
                </div>
              </li>
              <li class="nav-item">
                <div>
                  <a class="nav-link fw-bold text-dark pb-0" href="#">NẤM DƯỢC LIỆU</a>
                  <a class="nav-link fw-medium text-secondary pt-0" href="#">7 Sản Phẩm</a>
                </div>
              </li>
              <li class="nav-item">
                <div>
                  <a class="nav-link fw-bold text-dark pb-0" href="#">CHÀ BÔNG NẤM</a>
                  <a class="nav-link fw-medium text-secondary pt-0" href="#">3 Sản Phẩm</a>
                </div>
              </li>
              <li class="nav-item">
                <div>
                  <a class="nav-link fw-bold text-dark pb-0" href="#">PHÔI NẤM</a>
                  <a class="nav-link fw-medium text-secondary pt-0" href="#">1 Sản Phẩm</a>
                </div>
              </li>
              <li class="nav-item">
                <div>
                  <a class="nav-link fw-bold text-dark pb-0" href="#">NẤM QUÀ TẶNG</a>
                  <a class="nav-link fw-medium text-secondary pt-0" href="#">2 Sản Phẩm</a>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
