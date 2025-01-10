package com.example.webapp.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

import com.example.webapp.daos.UserDAO;
import com.example.webapp.models.User;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Kiểm tra thông tin đăng nhập
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmailAndPassword(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            // Phân quyền người dùng
            if (user.getIdRole() == 0) {
                response.sendRedirect("index.jsp"); // User
            } else if (user.getIdRole() == 1) {
                response.sendRedirect("admin-index.jsp"); // Admin
            } else {
                session.invalidate(); // Sai quyền
                request.setAttribute("error", "Quyền truy cập không hợp lệ!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            // Sai email hoặc mật khẩu
            request.setAttribute("error", "Email hoặc mật khẩu không chính xác!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}