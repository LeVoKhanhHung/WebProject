package servlets.order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import models.Order;
import daos.OrderDAO;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Lấy thông tin chi tiết đơn hàng từ Database
        Order order = OrderDAO.getOrderById(orderId);

        // Đặt thông tin vào request
        request.setAttribute("order", order);

        // Chuyển tiếp đến JSP hiển thị chi tiết đơn hàng
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-order-detail.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}