package Admin;

import Services.ServiceAddCategories;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        value = "/addCategories"
)
public class AddCategorie extends HttpServlet {
    ServiceAddCategories serviceAddCategories = new ServiceAddCategories();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
String categoryName = req.getParameter("categoryName");
String categoryDescription = req.getParameter("categoryDescription");
        try {
            serviceAddCategories.addCategories(categoryName,categoryDescription);
            System.out.println("Them thanh cong ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
