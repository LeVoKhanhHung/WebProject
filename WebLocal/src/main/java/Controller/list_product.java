package Controller;

import Services.ServiceProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        value = "/products"
)
public class list_product extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceProduct serviceProduct = new ServiceProduct();
      //  List<Product> products = serviceProduct.getProductList();
       // req.setAttribute("products",products);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
