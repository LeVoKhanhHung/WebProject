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
 String id  = req.getParameter("id");
        try {
            pro = serviceProduct.getProductDetail(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("product_detail",pro);
        req.getRequestDispatcher("product_detail.jsp").forward(req,resp);



    }
}
