package servlets.account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import models.User;
import daos.UserDAO;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");

        if (password.equals(confirmPassword)) {
            try {
                // Mã hóa mật khẩu bằng SHA-256
                String hashedPassword = hashPassword(password);

                // Gọi DAO để lưu thông tin người dùng vào database
                User user = new User();
                user.setUserName(fullName);
                user.setEmail(email);
                user.setUserPassword(hashedPassword); // Lưu mật khẩu đã mã hóa

                UserDAO userDAO = new UserDAO();
                boolean isRegistered = userDAO.registerUser(user);

                if (isRegistered) {
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("errorMessage", "Đăng ký thất bại. Vui lòng thử lại!");
                    request.getRequestDispatcher("sign-up.jsp").forward(request, response);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Đã xảy ra lỗi khi xử lý mật khẩu.");
                request.getRequestDispatcher("sign-up.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("sign-up.jsp").forward(request, response);
        }
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}