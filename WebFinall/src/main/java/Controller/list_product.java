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

