package com.example.webapp.controllers.feedback;

import com.example.webapp.daos.FeedbackDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FeedbackRespondController", value = "/admin/feedback/respond")
public class FeedbackRespondController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        String responseMessage = request.getParameter("responseMessage");

        // Gọi DAO để lưu phản hồi vào cơ sở dữ liệu
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        boolean isSuccess = feedbackDAO.saveResponse(feedbackId, responseMessage);

        // Chuyển hướng hoặc thông báo
        if (isSuccess) {
            response.sendRedirect(request.getContextPath() + "/admin/feedback?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/feedback?error=true");
        }
    }
}