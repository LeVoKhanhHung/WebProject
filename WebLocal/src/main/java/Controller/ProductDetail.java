package Controller;

import Models.Products.Products;
import Services.ServiceProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        value = "/product_detail"
)
public class ProductDetail extends HttpServlet {
    Products pro = new Products();
   ServiceProduct serviceProduct = new ServiceProduct();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            // Lấy thông tin sản phẩm theo ID
            Products product = serviceProduct.getProductDetail(id);

            // Lấy mô tả sản phẩm theo ID
            String productDescription = serviceProduct.getProductDescriptionById(Integer.parseInt(id));

            // Gắn thông tin sản phẩm vào session
            HttpSession session = req.getSession(true);
            session.setAttribute("product_detail", product);

            // Gắn mô tả sản phẩm vào request
            req.setAttribute("product_description", productDescription);

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy dữ liệu sản phẩm", e);
        }

        // Forward đến JSP để hiển thị
        req.getRequestDispatcher("product_detail.jsp").forward(req, resp);
    }
}
