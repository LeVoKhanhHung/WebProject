package com.example.webapp.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.example.webapp.models.Order;
import com.example.webapp.daos.OrderDAO;

@WebServlet(name = "Order", value = "/manage-order")
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Order> orders = OrderDAO.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/admin/manage-order.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể tải danh sách đơn hàng.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("update".equals(action)) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                String status = request.getParameter("status");
                if (OrderDAO.updateOrderStatus(orderId, status)) {
                    response.sendRedirect("/manage-order?success=updated");
                } else {
                    response.sendRedirect("/manage-order?error=updateFailed");
                }
            } else if ("delete".equals(action)) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                if (OrderDAO.deleteOrder(orderId)) {
                    response.sendRedirect("/manage-order?success=deleted");
                } else {
                    response.sendRedirect("/manage-order?error=deleteFailed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi xử lý yêu cầu.");
        }
    }
}