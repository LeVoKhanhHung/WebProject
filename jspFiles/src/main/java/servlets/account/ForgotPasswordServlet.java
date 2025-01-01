package servlets.account;

import daos.UserDAO;
import models.User;
import org.apache.catalina.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(email); // Tìm user theo email

        if (user != null) {
            // Tạo mật khẩu mới ngẫu nhiên
            String newPassword = generateRandomPassword();

            // Mã hóa mật khẩu mới
            String hashedPassword = null;
            try {
                hashedPassword = UserDAO.hashPassword(newPassword);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            // Cập nhật mật khẩu mới vào database
            boolean isUpdated = userDAO.updatePassword(user.getId(), hashedPassword);

            if (isUpdated) {
                // Gửi email mật khẩu mới
                boolean emailSent = sendEmail(email, "Khôi phục mật khẩu",
                        "Mật khẩu mới của bạn là: " + newPassword + "\nVui lòng đăng nhập và đổi lại mật khẩu.");

                if (emailSent) {
                    request.setAttribute("successMessage", "Mật khẩu mới đã được gửi tới email của bạn.");
                } else {
                    request.setAttribute("errorMessage", "Không thể gửi email. Vui lòng thử lại.");
                }
            } else {
                request.setAttribute("errorMessage", "Cập nhật mật khẩu thất bại. Vui lòng thử lại.");
            }
        } else {
            request.setAttribute("errorMessage", "Email không tồn tại trong hệ thống!");
        }

        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, 10);
    }

    // Gửi email
    private boolean sendEmail(String recipientEmail, String subject, String messageContent) throws MessagingException {
        // Cấu hình thông tin SMTP
        final String senderEmail = "your-email@gmail.com"; // Email của bạn
        final String senderPassword = "your-app-password"; // Mật khẩu ứng dụng
        final String smtpHost = "smtp.gmail.com";
        final int smtpPort = 587;

        // Thiết lập các thuộc tính JavaMail
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Phiên SMTP
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Tạo nội dung email
        Message message = new MimeMessage((MimeMessage) session);
        try {
            message.setFrom(new InternetAddress(senderEmail)); // Địa chỉ email gửi
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail) // Địa chỉ email nhận
            );
            message.setSubject("Khôi phục mật khẩu - Hệ thống của bạn");
            message.setText("Mật khẩu mới của bạn là: " + senderPassword + "\nHãy đăng nhập lại và đổi mật khẩu nếu cần.");

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công!");
        } catch (MessagingException e) {
            System.err.println("Gửi email thất bại. Vui lòng kiểm tra lại thông tin.");
            e.printStackTrace();
            throw e; // Báo lỗi nếu gửi không thành công
        }
        return false;
    }
}