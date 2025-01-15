package com.example.webapp.servlet;

import com.example.webapp.daos.AdminDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet(value = "/admin/home")
public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AdminDAO adminDAO = new AdminDAO();

            int totalProducts = adminDAO.getTotalProducts();
            int ordersToday = adminDAO.getOrdersToday();
            int newFeedbacks = adminDAO.getNewFeedbacks();
            List<Map<String, Object>> revenues = adminDAO.getAllRevenueData();

            // Truyền dữ liệu vào JSP
            request.setAttribute("totalProducts", totalProducts);
            request.setAttribute("ordersToday", ordersToday);
            request.setAttribute("newFeedbacks", newFeedbacks);
            request.setAttribute("revenues", revenues);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}