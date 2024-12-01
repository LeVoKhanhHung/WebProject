let cart = JSON.parse(localStorage.getItem('cart')) || [];
let lastProduct = cart[cart.length - 1];

// Hiển thị giá trị phí giao hàng
const shippingFee = localStorage.getItem('shippingFee');
if (shippingFee) {
    document.getElementById('phigiaohang').innerHTML = `Phí giao hàng: ${shippingFee} VND`;
}

// Cập nhật thông tin sản phẩm
document.getElementById('tensp').innerHTML = lastProduct.name;
document.getElementById('masp').innerHTML = lastProduct.id;
document.getElementById('countspthanhtoan').innerHTML = lastProduct.quantity;
document.getElementById('price').innerHTML = lastProduct.price;
document.getElementById('tamtinhprice').innerHTML = lastProduct.price;

var total = lastProduct.price * lastProduct.quantity;
document.getElementById('totalsp1').innerHTML = `${total} VND`;

// Hàm kiểm tra thông tin form
function validateForm() {
    const requiredFields = [
        { selector: ".inputname[placeholder='Họ và tên']", message: "Họ và tên là bắt buộc!" },
        { selector: ".inputname[placeholder='Nhập số điện thoại']", message: "Số điện thoại là bắt buộc!" },
        { selector: ".inputname[placeholder='Toà nhà, số nhà, tên đường']", message: "Địa chỉ là bắt buộc!" },
        { selector: ".inputname[placeholder='Xã phường/thị trấn']", message: "Xã phường/thị trấn là bắt buộc!" },
        { selector: ".inputname[type='email']", message: "Email là bắt buộc!" }
    ];

    // Kiểm tra từng trường bắt buộc
    for (let field of requiredFields) {
        const input = document.querySelector(field.selector);
        if (!input || !input.value.trim()) {
            alert(field.message);
            return false;  // Nếu có trường nào chưa điền, dừng lại và trả về false
        }
    }

    // Kiểm tra checkbox điều khoản
    const checkbox = document.querySelector(".formsubmit input[type='checkbox']");
    if (!checkbox.checked) {
        alert("Bạn phải đồng ý với điều khoản và điều kiện.");
        return false;
    }

    return true;  // Nếu tất cả các trường hợp đều hợp lệ
}

// Lắng nghe sự kiện submit cho form
document.querySelector(".formsubmit button").addEventListener("click", function(event) {
    if (!validateForm()) {
        event.preventDefault();  // Ngừng gửi form nếu không hợp lệ
    }
});
function toggleBox() {
    const box = document.getElementById("newBox");
    if (box.classList.contains("active")) {
        box.classList.remove("active");

        box.style.height = box.scrollHeight + 'px';
        setTimeout(() => (box.style.height = '0'), 0);
    } else {
        box.classList.add("active");

        box.style.height = box.scrollHeight + 'px';
        setTimeout(() => (box.style.height = 'auto'), 300);
    }
}
function toggleGuide() {
    var guide = document.getElementById("guide");
    guide.classList.toggle("hidden");
}
// Cập nhật số lượng sản phẩm
const subButtonthanhtoan = document.getElementById("subspthanhtoan");
const addButtonthanhtoan = document.getElementById("addspthanhtoan");
const countSPthanhtoan = document.getElementById("countspthanhtoan");

function updateCount(value) {
    let countProduct = parseInt(countSPthanhtoan.innerHTML);
    countProduct += value;
    if (countProduct < 1) countProduct = 1;
    countSPthanhtoan.innerHTML = countProduct;
}

subButtonthanhtoan.addEventListener('click', () => updateCount(-1));
addButtonthanhtoan.addEventListener('click', () => updateCount(1));
