// Xử lý đăng nhập
document.querySelector("form").addEventListener("submit", function (event) {
    event.preventDefault(); // Ngăn form gửi dữ liệu mặc định

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    const users = JSON.parse(localStorage.getItem("users")) || [];

    // Kiểm tra tài khoản
    const user = users.find(user => user.email === email && user.password === password);

    if (user) {
        sessionStorage.setItem("loggedInUser", email); // Lưu email người dùng
        sessionStorage.setItem("loggedInUserFullname", user.fullname); // Lưu fullname người dùng
        showTemporaryMessage(`Đăng nhập thành công! Chào mừng ${user.fullname}.`, 'success');
        setTimeout(function () {
            window.location.href = "index.html";
        }, 2000); // Chờ 1 giây rồi chuyển trang
    } else {
        showTemporaryMessage("Email hoặc mật khẩu không đúng. Vui lòng thử lại!", 'error');
    }
});

// Hàm hiển thị thông báo tạm thời
function showTemporaryMessage(message, type) {
    // Tạo thông báo
    const messageBox = document.createElement('div');
    messageBox.textContent = message;
    messageBox.classList.add('alert', type === 'success' ? 'alert-success' : 'alert-danger');
    messageBox.style.position = 'fixed';
    messageBox.style.top = '20px';
    messageBox.style.left = '50%';
    messageBox.style.transform = 'translateX(-50%)';
    messageBox.style.zIndex = '1000';
    messageBox.style.padding = '10px 20px';
    messageBox.style.borderRadius = '5px';
    messageBox.style.transition = 'opacity 1s';

    // Thêm vào body
    document.body.appendChild(messageBox);

    // Tự động ẩn sau 2 giây
    setTimeout(function () {
        messageBox.style.opacity = 0;
        setTimeout(function () {
            messageBox.remove();
        }, 2000); // Sau khi ẩn thì xóa thông báo
    }, 2000);
}
