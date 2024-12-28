package Admin;

import Services.ServiceAddSupplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
value = "/addSupplier"
        )
public class AddSuppllier extends HttpServlet {
    ServiceAddSupplier addSupplier = new ServiceAddSupplier();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
String supplierName = req.getParameter("supplierName");
String contactInfo = req.getParameter("contactInfo");
String address = req.getParameter("address");
int isActive = Integer.parseInt(req.getParameter("isActive"));
        try {
            if(addSupplier.addSupplier(supplierName,contactInfo,address,isActive)){
                System.out.println("Them nha cung cap thanh cong");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
