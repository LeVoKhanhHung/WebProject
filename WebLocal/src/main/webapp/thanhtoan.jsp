<%@ page import="Models.cart.Cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 15/12/2024
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thanhtoan</title>
  <link rel="stylesheet" href="css/styleThanhtoan.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">


  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


  <link rel="stylesheet" href="css/fontawesome.css">
  <link rel="stylesheet" href="css/templatemo-space-dynamic.css">
  <link rel="stylesheet" href="css/animated.css">
  <link rel="stylesheet" href="css/owl.css">
</head>
<style>
  .progress-header {
    background-color: #f8f8f8;
    padding: 20px 0;
    display: flex;
    justify-content: center;
  }

  /* Step Container */
  .step-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 80%;
    max-width: 800px;
  }

  /* Step Item */
  .step {
    display: flex;
    flex-direction: column;
    align-items: center;
    flex: 1;
    position: relative;
  }

  .step-icon {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: #ddd;
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: bold;
    font-size: 1rem;
    transition: all 0.3s ease;
  }

  .step-text {
    margin-top: 8px;
    font-size: 0.9rem;
    color: #888;
    text-transform: uppercase;
    font-weight: 600;
  }

  /* Line Between Steps */
  .step-line {
    height: 2px;
    background-color: #ddd;
    flex: 1;
  }

  /* Active Step */
  .step.active .step-icon {
    background-color: #ff7b00; /* Màu cam nổi bật */
  }

  .step.active .step-text {
    color: #ff7b00; /* Màu cam cho text */
  }
  body {
    background-color: #f8f9fa;
  }
  .order-summary-card, .payment-info-card {
    border: none;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin-top: 20px;
  }
  .order-summary-header, .payment-info-header {
    background-color: #6c5ce7;
    color: #fff;
    font-weight: bold;
    text-transform: uppercase;
    border-radius: 10px 10px 0 0;
    padding: 15px;
  }
  .product-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px dashed #ccc;
    padding: 10px 0;
  }
  .product-details img {
    width: 50px;
    height: 50px;
    margin-right: 15px;
  }
  .total-section {
    font-weight: bold;
    color: #2c3e50;
    font-size: 18px;
  }
  .container {
    max-width: 100%; /* Ví dụ: chỉnh max-width phù hợp với giao diện */
    margin: 0 0; /* Canh giữa */
    height: 200px;
  }


  .btn-payment {
    background-color: #ff7b00;
    color: white;
    width: 100%;
    font-size: 18px;
    padding: 12px 0;
    border: none;
    border-radius: 25px;
    transition: background-color 0.3s ease, transform 0.2s ease;
  }

  .btn-payment:hover {
    background-color: #e67e22;
    transform: scale(1.05);
  }

</style>

<body>
<%@include file="header.jsp"%>
<header class="progress-header">
  <div class="step-container">
    <!-- Step 1 -->
    <div class="step">
      <div class="step-icon">1</div>
      <div class="step-text">GIỎ HÀNG</div>
    </div>
    <!-- Line -->
    <div class="step-line"></div>
    <!-- Step 2 -->
    <div class="step active">
      <div class="step-icon">2</div>
      <div class="step-text">THANH TOÁN</div>
    </div>
    <!-- Line -->
    <div class="step-line"></div>
    <!-- Step 3 -->
    <div class="step">
      <div class="step-icon">3</div>
      <div class="step-text">HOÀN TẤT</div>
    </div>
  </div>
</header>
<section class="content" style="height: 1100px">
  <article class="khuyenmai">
    <p class="pkhuyenmai">
      Bạn có ưu đãi? <b onclick="toggleBox()">Ấn vào đây để nhập mã</b>
    </p>
    <article class="inputkhuyenmai" id = "newBox">
      <article class="textinput">
        Nếu bạn có mã giảm giá, vui lòng điền vào bên dưới.

      </article>
      <form action="" class="form">
        <textarea name="" id="" class="inputvaluekhuyenmai" placeholder="Nhập mã vào đây"></textarea>
        <button type="submit" class="button"> Xác nhận
        </button>
      </form>
    </article>
    <section class="contentproduct">
      <article class="inforkhuyenmai">
        <article class="icon">
          <i class="fa-solid fa-circle-exclamation"></i>
        </article>
        <article class="inforchitiet">
          <p>Để nhận
            "35 NAXA"
            Quý khách hãy
            <a href="http://">Đăng nhập</a>
            vào tài khoản. Nếu chưa có, Quý khách hãy tick
            <a href="http://">Tạo tài khoản mới?</a>
            nhé!</p>
        </article>
      </article>
      <form action="CheckoutServlet" method="post">
      <section class="information">

          <article class="inforfreeship">
            <p class="p">Thêm <span style="color: rgb(157, 56, 27); font-size: 15px;"> 455.000₫</span> nữa bạn sẽ được FREESHIP</p>
            <article class="rowfreeship">
              <article class="rowcheck">
              </article>
            </article>
          </article>
          <!--Thong tin ca  nhan de thanh toan-->
          <div class="container py-5" style="margin-left: -20px" >
            <div class="row">
              <!-- Thông Tin Thanh Toán -->
              <div class="col-md-6">
                <div class="card payment-info-card">
                  <div class="payment-info-header text-center" style="background-color: #ff7b00">
                    Thông Tin Thanh Toán
                  </div>
                  <div class="card-body">
                    <div class="mb-3">
                      <label for="fullName" class="form-label">Họ và Tên *</label>
                      <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Nhập họ và tên">
                    </div>
                    <div class="mb-3">
                      <label for="phoneNumber" class="form-label">Số Điện Thoại *</label>
                      <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Nhập số điện thoại">
                    </div>
                    <div class="mb-3">
                      <label for="email" class="form-label">Địa chỉ Email</label>
                      <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email (tùy chọn)">
                    </div>
                    <div class="row">
                      <div class="col-md-6 mb-3">
                        <label for="city" class="form-label">Tỉnh/Thành Phố *</label>
                        <select class="form-select" id="city" name="city" onchange="updateDistricts()">
                          <option value="">Chọn một tùy chọn...</option>
                          <option value="Hà Nội">Hà Nội</option>
                          <option value="Hồ Chí Minh">Hồ Chí Minh</option>
                          <option value="Đà Nẵng">Đà Nẵng</option>
                          <option value="Cần Thơ">Cần Thơ</option>
                        </select>
                      </div>
                      <div class="col-md-6 mb-3">
                        <label for="district" class="form-label">Quận/Huyện *</label>
                        <select class="form-select" id="district" name="district">
                          <option value="">Chọn quận/huyện...</option>
                        </select>
                      </div>
                    </div>


                    <div class="mb-3">
                      <label for="address" class="form-label">Địa chỉ *</label>
                      <input type="text" class="form-control" id="address" name="address" placeholder="Tòa nhà, số nhà, tên đường">
                    </div>
                    <div class="form-check mb-3">
                      <input type="checkbox" class="form-check-input" id="differentAddress" name="differentAddress">
                      <label class="form-check-label" for="differentAddress">Giao hàng tới địa chỉ khác?</label>
                    </div>
                    <div class="mb-3">
                      <label for="notes" class="form-label">Ghi Chú Đơn Hàng</label>
                      <textarea class="form-control" id="notes" name="notes" rows="3" placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay địa điểm giao hàng"></textarea>
                    </div>
                  </div>

                </div>
              </div>

              <!-- Thông Tin Đơn Hàng -->
              <div class="col-md-6">
                <div class="card order-summary-card">
                  <div class="order-summary-header text-center" style="background-color: #ff7b00;">
                    Thông Tin Đơn Hàng
                  </div>
                  <div class="card-body" style="overflow: scroll ;height: 300px">
                    <!-- Sản phẩm -->
                    <%
                      Cart cart = (Cart) session.getAttribute("cr7");
                      if (cart == null) {
                        cart = new Cart();
                        session.setAttribute("cr7", cart);
                      }
                      System.out.println("Session Cart: " + cart);
                      System.out.println("Number of items in cart: " + cart.getItems().size());

                    %>
                    <c:forEach var="item" items="${sessionScope.cr7.items}">
                      <div class="product-item">
                        <div class="product-details d-flex align-items-center">
                          <img src="img/nambaongu.jpg" alt="Product">
                          <div>
                            <div class="fw-bold">${item.name} - ${item.weight}gr</div>
                            <div>ID: ${item.id}</div>
                            <div>Số lượng: ${item.quantity}</div>

                          </div>
                        </div>
                        <!-- Giá cũ (gạch ngang) -->
                        <div class="old-price text-muted" style="text-decoration: line-through;">
                            ${item.total} đ
                        </div><span style="margin-left: -80px;color: red">(${item.sale}%)</span>
                        <!-- Tính giá mới -->
                        <c:set var="total" value="${item.total - (item.total * item.sale/100)}" />
                        <!-- Giá mới -->
                        <div class="new-price text-danger">
                            ${total} đ
                        </div>
                      </div>
                    </c:forEach>
                      <!-- Tổng cộng -->

                  </div>
                  <div class="total-section d-flex justify-content-between mt-4">
                    <span>Tổng cộng:</span>
                    <c:set var="sum" value="${sessionScope.cr7.price}" />


                    <span class="text-danger fs-5" id="Order">${sum} đ</span>
                  </div>



                  <!-- Phương Thức Thanh Toán -->
                  <div class="accordion mt-4" id="paymentAccordion">
                    <!-- COD - Thanh toán khi nhận hàng -->
                    <div class="accordion-item">
                      <h2 class="accordion-header" id="codHeader">
                        <input type="radio" name="paymentMethod" id="cod" class="me-2" onchange="handlePaymentSelection(this)" value="COD-Thanh toán khi nhận hàng">
                        <label for="cod" class="accordion-button collapsed">
                          <i class="fa-solid fa-truck me-2"></i>COD - Thanh toán khi nhận hàng
                        </label>
                      </h2>
                      <div id="codCollapse" class="accordion-collapse collapse show">
                        <div class="accordion-body">
                          <p>Thanh toán cho shipper khi nhận hàng.</p>
                          <!-- Phí vận chuyển -->
                          <div class="d-flex justify-content-between mb-2">
                            <span>Phí vận chuyển:</span>
                            <span class="fw-bold text-danger" id="pricecod">30,000 đ</span>
                          </div>
                          <!-- Mã giảm giá -->
                          <div class="input-group">
                            <input type="text" class="form-control" placeholder="Nhập mã giảm giá" id="codCoupon">
                            <button class="btn btn-success" type="button" onclick="applyCoupon()">Áp dụng</button>
                          </div>
                        </div>
                      </div>
                    </div>

                    <!-- Chuyển khoản ngân hàng -->
                    <div class="accordion-item">

                      <h2 class="accordion-header" id="bankHeader">
                        <input type="radio" name="paymentMethod" id="bank" class="me-2" onchange="handlePaymentSelection(this)" value="BANK-Chuyển khoản ngân hàng">
                        <label for="bank" class="accordion-button collapsed">
                          <i class="fa-solid fa-building-columns me-2"></i>Chuyển khoản ngân hàng
                        </label>
                      </h2>
                      <div id="bankCollapse" class="accordion-collapse collapse">
                        <div class="accordion-body">
                          <p>Chuyển khoản tới số tài khoản 123-456-789 (VCB).</p>
                          <!-- Phí vận chuyển -->
                          <div class="d-flex justify-content-between mb-2">
                            <span>Phí vận chuyển:</span>
                            <span class="fw-bold text-danger" id="pricebank">20,000 đ</span>
                          </div>
                          <!-- Mã giảm giá -->
                          <div class="input-group">
                            <input type="text" class="form-control" placeholder="Nhập mã giảm giá" id="bankCoupon" >
                            <button class="btn btn-success" type="button" onclick="applyCoupon()">Áp dụng</button>
                          </div>
                        </div>
                      </div>
                    </div>

                    <!-- Ví MoMo -->
                    <div class="accordion-item">
                      <h2 class="accordion-header" id="momoHeader">
                        <input type="radio" name="paymentMethod" id="momo" class="me-2" onchange="handlePaymentSelection(this)" value="MOMO-Thanh toán MOMO">
                        <label for="momo" class="accordion-button collapsed">
                          <i class="fa-solid fa-wallet me-2"></i>Ví MoMo
                        </label>
                      </h2>
                      <div id="momoCollapse" class="accordion-collapse collapse">
                        <div class="accordion-body">
                          <p>Quét mã QR hoặc gửi tiền đến số 0987654321.</p>
                          <!-- Phí vận chuyển -->
                          <div class="d-flex justify-content-between mb-2">
                            <span>Phí vận chuyển:</span>
                            <span class="fw-bold text-danger" id="pricemomo">25,000 đ</span>
                          </div>

                          <!-- Mã giảm giá -->
                          <div class="input-group">
                            <input type="text" class="form-control" placeholder="Nhập mã giảm giá" id="momoCoupon">
                            <button class="btn btn-success" type="button" onclick="applyCoupon()">Áp dụng</button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>


                  <button type="submit" class="btn-payment mt-4">Thanh Toán</button>

              </div>
            </div>
          </div>

      </section>
    </form>
    </section>
  </article>
</section>
<div style="width: 100%;height: 500px;background-color: rgba(240,234,226,0.9)">
  <%@include file="footer.jsp"
  %>

</div>


<script>
  function applyCoupon() {
    // Lấy giá trị mã giảm giá và phương thức thanh toán hiện tại
    const codCoupon = document.getElementById("codCoupon").value.trim();
    const bankCoupon = document.getElementById("bankCoupon").value.trim();
    const momoCoupon = document.getElementById("momoCoupon").value.trim();

    const codShippingFee = 30000;
    const bankShippingFee = 20000;
    const momoShippingFee = 25000;

    let currentPaymentMethod = document.querySelector('input[name="paymentMethod"]:checked');
    let discount = 0;
    let finalFee = 0;

    if (!currentPaymentMethod) {
      alert("Vui lòng chọn phương thức thanh toán!");
      return;
    }

    // Logic áp dụng giảm giá dựa trên phương thức thanh toán
    switch (currentPaymentMethod.id) {
      case "cod":
        discount = codCoupon === "GIAM10K" ? 10000 : 0; // Giảm 10,000 đ nếu đúng mã
        finalFee = Math.max(codShippingFee - discount, 0);
        alert(`Phí vận chuyển sau giảm:`+ finalFee.toLocaleString() +`đ`);
        document.getElementById("pricecod").innerHTML = finalFee.toLocaleString() +`đ`;
        var price =   document.getElementById("Order").innerText;
        console.log(price);
        var total = parseInt(price) + parseInt(finalFee);
        document.getElementById("Order").innerHTML = total.toLocaleString() + " đ";
        break;
      case "bank":
        discount = bankCoupon === "GIAM5K" ? 5000 : 0; // Giảm 5,000 đ nếu đúng mã
        finalFee = Math.max(bankShippingFee - discount, 0);
        alert(`Phí vận chuyển sau giảm:`+ finalFee.toLocaleString() +`đ`);
        document.getElementById("pricebank").innerHTML = finalFee.toLocaleString() +`đ`;
        var price =   document.getElementById("Order").innerText;
        console.log(price);
        var total = parseInt(price) + parseInt(finalFee);
        document.getElementById("Order").innerHTML = total.toLocaleString() + " đ";
        break;
      case "momo":
        discount = momoCoupon === "GIAM7K" ? 7000 : 0; // Giảm 7,000 đ nếu đúng mã
        finalFee = Math.max(momoShippingFee - discount, 0);
        alert(`Phí vận chuyển sau giảm:`+ finalFee.toLocaleString() +`đ`);
        document.getElementById("pricemomo").innerHTML = finalFee.toLocaleString() +`đ`;
        var price =   document.getElementById("Order").innerText;
        console.log(price);
        var total = parseInt(price) + parseInt(finalFee);
        document.getElementById("Order").innerHTML = total.toLocaleString() + " đ";
        break;
      default:
        alert("Mã giảm giá không hợp lệ hoặc phương thức thanh toán chưa chọn.");
    }
  }

  function handlePaymentSelection(selectedRadio) {
    // Lấy tất cả các phần tử collapse
    const paymentCollapses = document.querySelectorAll('.accordion-collapse');

    // Ẩn tất cả các phần collapse
    paymentCollapses.forEach((collapse) => {
      collapse.classList.remove('show');
    });

    // Hiển thị phần tương ứng với radio được chọn
    const selectedId = selectedRadio.id;
    const collapseId = selectedId + 'Collapse'; // Tạo id của phần collapse tương ứng
    const targetCollapse = document.getElementById(collapseId);

    if (targetCollapse) {
      targetCollapse.classList.add('show');
    }

    selectedRadio.form.submit();


  }

  const districts = {
    'Hà Nội': ['Ba Đình', 'Hoàn Kiếm', 'Đống Đa', 'Cầu Giấy', 'Thanh Xuân'],
    'Hồ Chí Minh': ['Quận 1', 'Quận 2', 'Quận 3', 'Quận 4', 'Quận 7'],
    'Đà Nẵng': ['Hải Châu', 'Liên Chiểu', 'Sơn Trà', 'Ngũ Hành Sơn', 'Cẩm Lệ'],
    'Cần Thơ': ['Ninh Kiều', 'Bình Thủy', 'Cái Răng', 'Ô Môn', 'Thới Lai']
  };

  function updateDistricts() {
    const city = document.getElementById('city').value;
    const districtSelect = document.getElementById('district');

    // Clear the current options
    districtSelect.innerHTML = '<option value="">Chọn quận/huyện...</option>';

    if (city !== '') {
      const districtsList = districts[city];
      districtsList.forEach(district => {
        const option = document.createElement('option');
        option.value = district;
        option.text = district;
        districtSelect.add(option);
      });
    }
  }
</script>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/scripthanhtoan.js"></script>
</html>