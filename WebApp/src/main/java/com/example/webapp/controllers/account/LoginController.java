package com.example.webapp.controllers.account;

import com.example.webapp.daos.UserDAO;
import com.example.webapp.models.user.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Kiểm tra thông tin user từ database (thông qua DAO)
        UserDAO userDAO = new UserDAO();
        User user = userDAO.checkLogin(email, password);

        if (user != null) {
            // Đăng nhập thành công
            HttpSession session = request.getSession();
            session.setAttribute("idUser", user.getId());
            session.setAttribute("idRole", user.getIdRole());
            session.setAttribute("userName", user.getUserName());

            // Chuyển hướng dựa trên idRole
            if (user.getIdRole() == 1) {
                // Nếu là admin, chuyển đến trang admin
                response.sendRedirect(request.getContextPath() + "/admin/home");
            } else {
                // Nếu là user thường, chuyển đến trang index (khách hàng)
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } else {
            // Đăng nhập thất bại, quay lại trang login với thông báo lỗi
            request.setAttribute("errorMessage", "Email hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}