// Xử lý đăng nhập
document.querySelector("form").addEventListener("submit", function (event) {
    event.preventDefault(); // Ngăn form gửi dữ liệu mặc định

    // Lấy giá trị từ các trường nhập liệu
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    // Truy xuất danh sách người dùng từ LocalStorage
    const users = JSON.parse(localStorage.getItem("users")) || [];

    // Kiểm tra thông tin đăng nhập
    const user = users.find(user => user.email === email && user.password === password);
    if (user) {
        alert(`Đăng nhập thành công! Chào mừng ${user.fullname}.`);
        sessionStorage.setItem("loggedInUser", email); // Lưu email người dùng
        sessionStorage.setItem("loggedInUserFullname", user.fullname); // Lưu fullname người dùng
        window.location.href = "index.html";
    } else {
        alert("Email hoặc mật khẩu không đúng. Vui lòng thử lại!");
    }
});