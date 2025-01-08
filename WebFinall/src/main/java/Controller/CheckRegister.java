package Controller;

import Services.ServiceRegister;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        value = "/checkRegister"
)
public class CheckRegister extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServiceRegister serviceRegister = new ServiceRegister();
//        String fullName = req.getParameter("fullName");
//        String email = req.getParameter("email");
//        String phoneNumber = req.getParameter("phoneNumber");
//        String password = req.getParameter("password");
//        String confirmPassword = req.getParameter("confirmPassword");
//        String agreeTerms = req.getParameter("agreeTerms"); // Trả về "on" nếu được chọn, null nếu không
//
//        // Kiểm tra dữ liệu (ví dụ: kiểm tra mật khẩu khớp, kiểm tra đồng ý điều khoản)
//        if (fullName == null || fullName.isEmpty() ||
//                email == null || email.isEmpty() ||
//                phoneNumber == null || phoneNumber.isEmpty() ||
//                password == null || password.isEmpty() ||
//                confirmPassword == null || confirmPassword.isEmpty()) {
//            resp.getWriter().println("Vui lòng điền đầy đủ thông tin.");
//            return;
//        }
//
//        if (!password.equals(confirmPassword)) {
//            resp.getWriter().println("Mật khẩu và nhập lại mật khẩu không khớp.");
//            return;
//        }
//
//        if (agreeTerms == null) {
//            resp.getWriter().println("Bạn cần đồng ý với điều khoản sử dụng.");
//            return;
//        }
//
//        try {
//
//            if(serviceRegister.addAccount(email,password,fullName,phoneNumber)){
//               int idUser =  serviceRegister.getID(email);
//                HttpSession session = req.getSession(true);
//                session.setAttribute("idUser",idUser);
//                resp.sendRedirect("index.jsp");
//
//
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
}
