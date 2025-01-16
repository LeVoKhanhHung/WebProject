package com.example.webapp.servlet;


import com.example.webapp.models.user.UserAdmin;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

import com.example.webapp.daos.UserDAO;
import com.example.webapp.models.user.User;

@WebServlet(value = "/admin/manage-user")
public class ManageUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<UserAdmin> users = userDAO.getAllUsersAdmin();
        request.setAttribute("userList", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/manage-user.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDAO userDAO = new UserDAO();
        boolean isActive = "activate".equals(action);

        boolean success = userDAO.updateUserStatus(userId, isActive);

        if (success) {
            response.sendRedirect("/admin/manage-user");
        } else {
            response.getWriter().write("Lỗi khi cập nhật trạng thái người dùng.");
        }
    }
}