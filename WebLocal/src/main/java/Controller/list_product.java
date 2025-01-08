package Controller;

import Models.Product.ListProduct;
import Services.ServiceProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        value = "/products"
)
public class list_product extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceProduct serviceProduct = new ServiceProduct();
        HttpSession session = req.getSession(true);

        int page = 1; // Mặc định là trang 1
        int itemsPerPage = 9; // Số sản phẩm hiển thị mỗi trang

	 // danh mục có ten trong danh sách
        List<String> categories = List.of("NẤM QUÀ TẶNG", "BỘT NẤM ĂN", "NẤM DƯỢC LIỆU", "CHÀ BÔNG NẤM", "NẤM TƯƠI", "NẤM KHÔ", "PHÔI NẤM");

        // số lượng sản phẩm cho theo loại danh mục
        Map<String, Integer> categoryProductCounts = new HashMap<>();
        for (String category : categories) {
            try {
                int count = serviceProduct.getCategoryProductCounts(category);
                categoryProductCounts.put(category, count);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	// Kiểm tra tham số `page` từ request
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
            session.setAttribute("currentPage", page); // Lưu vào session
        } else if (session.getAttribute("currentPage") != null) {
            page = (int) session.getAttribute("currentPage");
        }

	try {
            // Tổng số sản phẩm
            int totalProducts = serviceProduct.getTotalProducts();
            // Tổng số trang
            int totalPages = (int) Math.ceil((double) totalProducts / itemsPerPage);

            // Kiểm tra tham số lọc từ request
            String filterType = req.getParameter("filterType");
            if (filterType == null || filterType.isEmpty()) {
                filterType = "default"; // Lọc mặc định nếu không có tham số
            }
            // Lấy danh sách sản phẩm của trang hiện tại
//            ListProduct item = serviceProduct.getListProductByPage(page, itemsPerPage);
            ListProduct filteredProducts = serviceProduct.getFilteredProducts(filterType, page, itemsPerPage);

            // Lưu thông tin vào session
//            session.setAttribute("listproduct", item);
            session.setAttribute("listproduct", filteredProducts);
            session.setAttribute("totalPages", totalPages);
            session.setAttribute("categoryProductCounts", categoryProductCounts);

            // Chuyển tiếp tới JSP
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    }

