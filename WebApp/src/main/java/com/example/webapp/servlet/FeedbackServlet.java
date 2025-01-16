package com.example.webapp.servlet;

import com.example.webapp.daos.FeedbackDAO;
import com.example.webapp.models.feedbacks.FeedbackAdmin;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/feedback")
public class FeedbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<FeedbackAdmin> feedbackList = feedbackDAO.getAllFeedbacksWithUserInfo();
        request.setAttribute("feedbacks", feedbackList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/feedback.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}