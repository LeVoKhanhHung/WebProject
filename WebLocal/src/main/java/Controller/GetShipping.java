package Controller;

import Models.Shipping.Shipping;
import Services.ServiceShipping;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        value = "/getShipping"
)
public class GetShipping extends HttpServlet {
    ServiceShipping serviceShipping = new ServiceShipping();
    public Shipping items;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            items= serviceShipping.getShipping();
            System.out.println(items.shippingdetailList.size());
            HttpSession session = req.getSession(true);
            session.setAttribute("totalship",items);
            req.getRequestDispatcher("thanhtoan.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
