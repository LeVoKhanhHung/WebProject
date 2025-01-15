package com.example.webapp.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.example.webapp.models.User;
import com.example.webapp.services.UserService;

@WebServlet(name = "ManageUserController", value = "/manage-user")
public class ManageUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("viewAll".equals(action)) {
            List<User> users = UserService.getAllUsers();
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user-list.jsp"); // Chuyển đến trang danh sách người dùng
            dispatcher.forward(request, response);
        } else if ("view".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = UserService.getUserById(id);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user-details.jsp"); // Chuyển đến trang chi tiết người dùng
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String email = request.getParameter("email");
            String userPassword = request.getParameter("userPassword");
            String userName = request.getParameter("userName");
            String phoneNumber = request.getParameter("phoneNumber");
            String birthDateStr = request.getParameter("birthDate");
            String companyName = request.getParameter("companyName");
            String address = request.getParameter("address");
            String image = request.getParameter("image");
            int point = Integer.parseInt(request.getParameter("point"));
            int idFavoriteProduct = Integer.parseInt(request.getParameter("idFavoriteProduct"));
            int idRole = Integer.parseInt(request.getParameter("idRole"));
            String createDateStr = request.getParameter("createDate");
            boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

            // Chuyển đổi String thành Date (ví dụ: từ chuỗi "2025-01-01" thành đối tượng java.util.Date)
            java.util.Date birthDate = java.sql.Date.valueOf(birthDateStr);
            java.util.Date createDate = java.sql.Date.valueOf(createDateStr);

            User user = new User(0, email, userPassword, userName, phoneNumber, birthDate, companyName, address, image, point, idFavoriteProduct, idRole, createDate, isActive);
            boolean isAdded = UserService.addUser(user);

            if (isAdded) {
                response.sendRedirect("UserController?action=viewAll"); // Sau khi thêm thành công, chuyển về trang danh sách người dùng
            } else {
                response.sendRedirect("admin/user-add.jsp"); // Nếu thêm thất bại, quay lại trang thêm người dùng
            }
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String email = request.getParameter("email");
            String userPassword = request.getParameter("userPassword");
            String userName = request.getParameter("userName");
            String phoneNumber = request.getParameter("phoneNumber");
            String birthDateStr = request.getParameter("birthDate");
            String companyName = request.getParameter("companyName");
            String address = request.getParameter("address");
            String image = request.getParameter("image");
            int point = Integer.parseInt(request.getParameter("point"));
            int idFavoriteProduct = Integer.parseInt(request.getParameter("idFavoriteProduct"));
            int idRole = Integer.parseInt(request.getParameter("idRole"));
            String createDateStr = request.getParameter("createDate");
            boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

            // Chuyển đổi String thành Date (ví dụ: từ chuỗi "2025-01-01" thành đối tượng java.util.Date)
            java.util.Date birthDate = java.sql.Date.valueOf(birthDateStr);
            java.util.Date createDate = java.sql.Date.valueOf(createDateStr);

            User user = new User(id, email, userPassword, userName, phoneNumber, birthDate, companyName, address, image, point, idFavoriteProduct, idRole, createDate, isActive);
            boolean isUpdated = UserService.updateUser(user);

            if (isUpdated) {
                response.sendRedirect("UserController?action=viewAll"); // Sau khi cập nhật thành công, chuyển về trang danh sách người dùng
            } else {
                response.sendRedirect("#"); // Nếu cập nhật thất bại, quay lại trang cập nhật người dùng
            }
        }
    }

    // Phương thức xóa người dùng
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isDeleted = UserService.deleteUser(id);

        if (isDeleted) {
            response.sendRedirect("UserController?action=viewAll"); // Sau khi xóa thành công, chuyển về trang danh sách người dùng
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete user");
        }
    }
}