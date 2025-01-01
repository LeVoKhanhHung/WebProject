package servlets.order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import daos.OrderDAO;

@WebServlet("/deleteOrder")
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Xóa đơn hàng từ cơ sở dữ liệu
        boolean success = OrderDAO.deleteOrder(orderId);

        // Nếu xóa thành công, chuyển hướng lại về trang quản lý đơn hàng
        if (success) {
            response.sendRedirect("admin-manage-order.jsp");
        } else {
            // Nếu thất bại, có thể thông báo lỗi
            request.setAttribute("errorMessage", "Xóa đơn hàng không thành công.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-manage-order.jsp");
            dispatcher.forward(request, response);
        }
    }
}