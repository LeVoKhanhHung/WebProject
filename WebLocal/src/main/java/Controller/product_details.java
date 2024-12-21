package Controller;

import Models.cart.Cart;
import Models.Productt;
import Services.ServiceProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        value = "/product_detail"
)
public class product_details extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServiceProduct serviceProduct = new ServiceProduct();
        String id = req.getParameter("productID");
        String weight = req.getParameter("weight");
        System.out.println(id);
        System.out.println(weight);

        Productt pro = null;
HttpSession session = req.getSession(true);
        try {
            pro = serviceProduct.getById(String.valueOf(id), Integer.parseInt(weight));
            Cart cart = (Cart) session.getAttribute("cr7");
            if(cart == null){
                cart = new Cart();
            }

                cart.addCart(pro);


            session.setAttribute("cr7",cart);
            req.getRequestDispatcher("product_detail.jsp").forward(req,resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }







    }
}
