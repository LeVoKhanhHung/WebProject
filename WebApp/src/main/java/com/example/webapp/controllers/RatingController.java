package com.example.webapp.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.webapp.models.Rating;
import com.example.webapp.daos.RatingDAO;

@WebServlet(name = "RatingController", value = "/RatingController")
public class RatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Rating> ratings = RatingDAO.getAllRatings();
            request.setAttribute("ratings", ratings);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/manage-rating.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải danh sách đánh giá.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String message = null;

        try {
            if ("delete".equals(action)) {
                int ratingId = Integer.parseInt(request.getParameter("ratingId"));
                if (RatingDAO.deleteRating(ratingId)) {
                    message = "Đánh giá đã được xóa thành công!";
                } else {
                    message = "Không thể xóa đánh giá!";
                }
            } else if ("respond".equals(action)) {
                int ratingId = Integer.parseInt(request.getParameter("ratingId"));
                String responseText = request.getParameter("responseText");
                if (RatingDAO.respondToRating(ratingId, responseText)) {
                    message = "Phản hồi đã được gửi!";
                } else {
                    message = "Không thể gửi phản hồi!";
                }
            }
            // Truyền thông báo đến JSP
            request.setAttribute("message", message);
            response.sendRedirect(request.getContextPath() + "/RatingServlet");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi xử lý yêu cầu.");
        }
    }
}