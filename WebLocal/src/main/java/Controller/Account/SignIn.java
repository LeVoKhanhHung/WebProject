package Controller.Account;

import Services.ServiceUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(
        value = "/checkLogin"
)
public class SignIn extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String hassPassword;
        HttpSession session = req.getSession(true);
        try {
            hassPassword = serviceUser.hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (serviceUser.checkCredentials(email,hassPassword)){
            int idUser = 0;
            try {
                idUser = serviceUser.check(email,hassPassword);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("idUser",idUser);
            resp.sendRedirect("products");

        }else{
            resp.sendRedirect("admin/login.jsp");
        }
    }
}
