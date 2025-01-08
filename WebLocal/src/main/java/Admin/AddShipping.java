package Admin;

import Services.ServiceShipping;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        value = "/addShipping"
)
public class AddShipping extends HttpServlet {
    ServiceShipping serviceShipping = new ServiceShipping();
    public AddShipping items;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameShipping = req.getParameter("deliveryService");
        double price = Double.parseDouble(req.getParameter("price"));
        try {
            serviceShipping.addShipping(nameShipping,price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
