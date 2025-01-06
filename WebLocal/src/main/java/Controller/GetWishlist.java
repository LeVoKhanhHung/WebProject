package Controller;

import Models.WishlistProduct.WishlistProduct;
import Services.ServiceWishlist;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        value = "/getWishlist"
)
public class GetWishlist extends HttpServlet {

    ServiceWishlist serviceWishlist = new ServiceWishlist();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Có gọi không?");

        // Lấy session và kiểm tra idUser
        HttpSession session = req.getSession(true);
        Integer idUser = (Integer) session.getAttribute("idUser");

        if (idUser == null) {
            // Nếu không có idUser trong session, chuyển hướng đến trang đăng nhập
            resp.sendRedirect("login.jsp");
            return;
        }

        // Lấy idProduct và weight từ request
        int idProduct;
        double weight;

        try {
            idProduct = Integer.parseInt(req.getParameter("productID"));
            weight = Double.parseDouble(req.getParameter("weight"));
        } catch (NumberFormatException e) {
            // Nếu có lỗi trong việc phân tích tham số, thông báo lỗi cho người dùng
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tham số không hợp lệ.");
            return;
        }

        try {
            // Gọi phương thức selectWishlist từ ServiceWishlist
            WishlistProduct wishlistProduct = serviceWishlist.selectWishlist(idUser, idProduct, weight);

            // Nếu tìm thấy sản phẩm trong wishlist, lưu vào session
            if (wishlistProduct != null) {
                session.setAttribute("wishlist", wishlistProduct);
            }

            // Chuyển hướng đến trang history.jsp
            resp.sendRedirect("history.jsp");

        } catch (SQLException e) {
            // Log và xử lý lỗi SQL
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi truy vấn cơ sở dữ liệu.");
        }
    }
}