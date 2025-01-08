<%@ page import="Models.inforTransaction.Transaction" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="Models.WishlistProduct.WishlistProduct" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 21/12/2024
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lịch sử giao dịch</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        :root {
            --main-color: #ff5722; /* Màu cam chủ đạo */
            --light-color: #ffe7d9;
        }

        body {
            background-color: #f9f9f9;
            font-family: 'Arial', sans-serif;
        }

        .sidebar {
            background-color: var(--light-color);
            border-radius: 10px;
        }

        .sidebar .list-group-item {
            border: none;
        }

        .sidebar .list-group-item.active {
            background-color: var(--main-color);
            color: #fff;
            font-weight: bold;
        }

        .btn-primary {
            background-color: var(--main-color);
            border: none;
        }

        .btn-primary:hover {
            background-color: #ff5722;
        }

        .card {
            border-radius: 10px;
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .card-body {
            padding: 1.5rem;
        }

        .table th {
            background-color: var(--light-color);
        }

        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #fff;
        }

        .table-striped tbody tr:nth-of-type(even) {
            background-color: var(--light-color);
        }

        .form-select, .form-control {
            border-radius: 20px;
        }

        .user-avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid var(--main-color);
        }

        h4 {
            color: var(--main-color);
        }

        .detail-section {
            display: none;
            margin-top: 20px;
        }

        .detail-section.active {
            display: block;
        }
        .product-item {
            background-color: #fff;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
        }

        .product-details {
            display: flex;
            flex-direction: column;
            gap: 5px;
        }

        .product-id, .product-price, .product-name {
            font-size: 14px;
            color: #333;
        }

        .product-id {
            color: #ff5722;
        }

        .product-price {
            color: #4caf50;
        }

        .product-name {
            font-weight: bold;
        }
        .detail-section {
            display: none;
            margin-top: 20px;
            transform: translateY(20px);
            opacity: 0;
            transition: transform 0.3s ease, opacity 0.3s ease;
        }

        .detail-section.active {
            display: block;
            transform: translateY(0);
            opacity: 1;
        }

        .product-item {
            background-color: #fff;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            opacity: 0;
            animation: fadeIn 0.6s ease forwards;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
        .view-detail.clicked {
            background-color: #007bff;  /* Màu xanh dương khi nhấn */
            color: #fff;  /* Đổi màu chữ */
            border: 1px solid #0056b3;  /* Thêm đường viền khi nhấn */
            transition: background-color 0.3s ease, color 0.3s ease;
        }

td img{
    width: 60px;
    height: 60px;
    border-radius: 10px;
}
    </style>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container mt-5">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-3">
            <div class="sidebar p-4">
                <div class="text-center mb-4">
                    <img src="https://via.placeholder.com/100" alt="User Avatar" class="user-avatar mb-3">
                    <h5>Nguyễn Bùi Hoàng Vũ</h5>
                    <p class="text-muted">Tham gia từ 06/2022</p>
                    <button class="btn btn-primary w-100">Thông tin</button>
                </div>
                <ul class="list-group" style="cursor: pointer" id="menuList">
                    <li class="list-group-item">Quản lý</li>
                    <li class="list-group-item">Nạp tiền</li>
                    <li class="list-group-item">Mã thưởng</li>
                  <li class="list-group-item" onclick="checkNone()">Sản phẩm yêu thích</li>
                    <li class="list-group-item active" onclick="checkBlock()">Lịch sử giao dịch</li>
                    <li class="list-group-item">Tìm kiếm đã lưu</li>
                </ul>
            </div>
        </div>
<%

    Transaction transaction = (Transaction) session.getAttribute("transactions");
    if (transaction != null && transaction.getItems() != null && !transaction.getItems().isEmpty()) {
        for (int i = 0; i < transaction.getItems().size(); i++) {
            System.out.println(transaction.getItems().get(i).getProducts());
            System.out.println( "Size cua transaction " + transaction.getItems().size());
        }// Kiểm tra in ra dữ liệu
    } else {
        System.out.println("Transaction hoặc danh sách items trống!");
    }
    WishlistProduct wishlistProduct = (WishlistProduct) session.getAttribute("wishlist");
    if(wishlistProduct == null){
        wishlistProduct = new WishlistProduct();
        session.setAttribute("wishlist",wishlistProduct);
    }
    System.out.println(wishlistProduct.getItems().size() + "wishlist");

%>
        <!-- Content -->
        <div class="col-md-9" id="ls">
            <h4 class="mb-4">Lịch sử giao dịch</h4>
            <div class="card">
                <div class="card-body">
                    <!-- Filters -->
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <select class="form-select w-auto">

                            <option>Mua hàng</option>

                        </select>
                        <input type="text" class="form-control w-50" placeholder="Tìm theo mã giao dịch">
                    </div>


                    <!-- Transaction Table -->
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Mã giao dịch</th>
                            <th>Ngày giao dịch</th>
                            <th>Loại giao dịch</th>
                            <th>Số tiền</th>
                            <th>Chi tiết</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${sessionScope.transactions.items}">
                        <tr>
                            <td>${item.idOrder}</td>
                            <td>${item.transactionDate}</td>
                            <td>Thanh toán</td>
                            <td> <fmt:formatNumber value="${item.totalPrice}" type="number" groupingUsed="true" /> đ</td>
                            <td><button class="btn btn-sm btn-primary view-detail" data-id-order="${item.idOrder}">Xem chi tiết</button></td>
                        </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>

            <!-- Detail Section -->
            <div class="card detail-section" id="detail-section">
                <div class="card-body">
                    <h5>Chi tiết giao dịch</h5>
                    <ul id="detail-list">
                        <!-- Chi tiết sản phẩm sẽ được thêm vào đây -->
                    </ul>
                    <button class="btn btn-secondary" id="close-detail">Đóng</button>
                </div>
            </div>
        </div>
        <!-- Menu yeu thich -->
        <div class="col-md-9" style="display: none" id="check" >
            <h4 class="mb-4">Sản phẩm yêu thích</h4>
            <div class="card">
                <div class="card-body">
                    <!-- Filters -->
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <select class="form-select w-auto">

                            <option>Sản phẩm yêu thích</option>

                        </select>
                        <input type="text" class="form-control w-50" placeholder="Tìm theo mã sản phẩm">
                    </div>


                    <!-- Transaction Table -->
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Hình ảnh</th>
                            <th>Tên sản phẩm</th>
                            <th>Mã sản phẩm</th>
                            <th>Ngày thêm</th>
                            <th>Xoá</th>


                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${ not empty sessionScope.wishlist.items}">

                            <c:forEach var="item" items="${sessionScope.wishlist.items}">
                                <tr>
                                    <td><img src="img/${item.img}"></td>
                                    <td>${item.name}</td>
                                    <td>${item.id}</td>
                                    <td>${item.date}</td>
                                    <td style="cursor: pointer"><i class="fa-regular fa-trash-can"></i></td>


                                </tr>
                            </c:forEach>
                        </c:if>


                        </tbody>

                    </table>
                </div>
            </div>

            <!-- Detail Section -->
            <div class="card detail-section" id="detail-section1">
                <div class="card-body">
                    <h5>Chi tiết giao dịch</h5>
                    <ul id="detail-list1">
                        <!-- Chi tiết sản phẩm sẽ được thêm vào đây -->
                    </ul>
                    <button class="btn btn-secondary" id="close-detail1">Đóng</button>
                </div>
            </div>
        </div>
</div>
</div>

<%@include file="footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        const detailSection = document.getElementById('detail-section');
        const detailList = document.getElementById('detail-list');
        const closeDetail = document.getElementById('close-detail');

        // Lấy tất cả các giao dịch từ session (chuỗi JSON)
        const transactions = <%= new ObjectMapper().writeValueAsString(transaction.getItems()) %>;

        // Khi nhấn nút "Xem chi tiết"
        document.querySelectorAll('.view-detail').forEach(button => {
            button.addEventListener('click', () => {
                const idOrder = button.getAttribute('data-id-order');
                console.log("ID Order: " + idOrder); // In ra idOrder để kiểm tra

                // Tìm giao dịch tương ứng với idOrder
                const selectedTransaction = transactions.find(t => t.idOrder === parseInt(idOrder));
                console.log("Selected Transaction: ", selectedTransaction); // In ra giao dịch chọn được

                if (selectedTransaction && selectedTransaction.products) {
                    // Kiểm tra từng sản phẩm và in ra console
                    selectedTransaction.products.forEach(product => {
                        console.log(product.price); // In giá sản phẩm ra console
                    });

                    // Hiển thị danh sách sản phẩm
                    detailList.innerHTML = selectedTransaction.products.map(product => {
                        console.log(product.id); // In id sản phẩm ra console

                        return '<li class="product-item">' +
                            '<div class="product-details">' +
                            '<span class="product-id"><strong>ID sản phẩm:</strong> ' + product.idProduct + '</span>' +
                            '<span class="product-price"><strong>Giá sản phẩm:</strong> ' + product.price + ' đ</span>' +
                            '<span class="product-name"><strong>Tên sản phẩm:</strong> ' + product.nameProduct + '</span>' +
                            '<span class="product-quantity"><strong>Số lượng:</strong> ' + product.quantity + '</span>' + // Thêm số lượng
                            '</div>' +
                            '</li>';

                    }).join('');  // join() để gộp các phần tử mảng thành một chuỗi

                    detailSection.classList.add('active');
                } else {
                    detailList.innerHTML = '<li>Không có sản phẩm nào.</li>';
                }
            });
        });

        // Đóng chi tiết
        closeDetail.addEventListener('click', () => {
            // Xóa hiệu ứng nhấn khi đóng chi tiết
            document.querySelectorAll('.view-detail').forEach(button => {
                button.classList.remove('clicked');
            });

            // Thêm hiệu ứng đóng phần chi tiết
            detailSection.classList.remove('active');
        });
    });
    function checkNone(){
        document.getElementById("check").style.display = "block";
        document.getElementById("ls").style.display="none";

    }
    function checkBlock(){
        document.getElementById("check").style.display = "none";
        document.getElementById("ls").style.display="block";
    }
    document.addEventListener('DOMContentLoaded', () => {
        // Lấy tất cả các phần tử <li> trong danh sách
        const listItems = document.querySelectorAll('#menuList .list-group-item');

        // Gắn sự kiện click cho từng phần tử
        listItems.forEach(item => {
            item.addEventListener('click', () => {
                // Xóa class active khỏi tất cả các <li>
                listItems.forEach(li => li.classList.remove('active'));

                // Thêm class active vào <li> được nhấp
                item.classList.add('active');
            });
        });
    });
</script>

</body>
</html>

