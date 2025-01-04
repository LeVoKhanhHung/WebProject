package Controller;

import Models.Product.ListProduct;
import Services.ServiceProductCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        value = "/product_category"
)
public class Product_Category extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCategory = req.getParameter("idCategory");
        String pageParam = req.getParameter("page");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        ServiceProductCategory serviceProductCategory = new ServiceProductCategory();
        HttpSession session = req.getSession(true);

        try {
            int totalProducts = serviceProductCategory.getTotalProducts(Integer.parseInt(idCategory));
            int productsPerPage = 9; // Số sản phẩm mỗi trang
            int totalPages = (int) Math.ceil((double) totalProducts / productsPerPage);

            // Lấy danh sách sản phẩm của trang hiện tại
            ListProduct items = serviceProductCategory.getProductCategory(Integer.parseInt(idCategory), page, productsPerPage);

            // Lưu thông tin vào session
            session.setAttribute("listproduct", items);
            session.setAttribute("totalPages", totalPages);
            session.setAttribute("currentPage", page);

            // Chuyển tiếp tới JSP
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }


