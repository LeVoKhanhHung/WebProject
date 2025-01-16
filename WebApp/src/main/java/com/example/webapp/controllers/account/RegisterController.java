package com.example.webapp.controllers.account;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.example.webapp.models.user.User;
import com.example.webapp.daos.UserDAO;

@WebServlet(name = "RegisterController", value = "/register-controller")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chỉ cần hiển thị form đăng ký khi GET
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ form đăng ký
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userName = request.getParameter("fullname");
        String phoneNumber = request.getParameter("phoneNumber");
        String birthDateStr = request.getParameter("birthDate");
        String companyName = request.getParameter("companyName");
        String address = request.getParameter("address");
        String image = request.getParameter("image");

        // Parse birth date
        java.util.Date birthDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birthDate = sdf.parse(birthDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tạo đối tượng User
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUserPassword(password); // Giả sử password đã được mã hóa ngoài
        newUser.setUserName(userName);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setBirthDate(birthDate);
        newUser.setCompanyName(companyName);
        newUser.setAddress(address);
        newUser.setImage(image);
        newUser.setPoint(0); // Khởi tạo điểm người dùng mặc định
        newUser.setIdFavoriteProduct(0); // Mặc định không có sản phẩm yêu thích
        newUser.setIdRole(1); // Giả sử idRole = 1 là người dùng bình thường
        newUser.setCreateDate(new java.util.Date());
        newUser.setActive(true); // Mặc định người dùng hoạt động

        // Gọi DAO để lưu người dùng vào database
        UserDAO userDAO = new UserDAO();
        boolean isRegistered = userDAO.registerUser(newUser);

        // Điều hướng đến trang kết quả
        if (isRegistered) {
            response.sendRedirect("login.jsp"); // Đăng ký thành công, chuyển sang trang đăng nhập
        } else {
            request.setAttribute("error", "Đăng ký không thành công!");
            request.getRequestDispatcher("register.jsp").forward(request, response); // Quay lại trang đăng ký
        }
    }
}