package Controller.Account;

import Models.User.User;


import Services.ServiceUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import java.security.SecureRandom;
import java.util.Base64;


@WebServlet(
        value = "/forgotPassword"
)
public class ForgotPasswordServlet extends HttpServlet  {
    ServiceUser serviceUser = new ServiceUser();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = serviceUser.getUserByEmail(email);
        System.out.println(user.getEmail());
        String hashedPassword = null;
        String newPassword = null;
        if(user != null){
             newPassword = serviceUser.generateRandomPassword();

            try {
                hashedPassword = serviceUser.hashPassword(newPassword);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        }
        boolean isUpdated = serviceUser.updatePassword(user.getId(), hashedPassword);
        if(isUpdated){
            String subject = "Thông Báo Đổi Mật Khẩu Tài Khoản Của Bạn";
            String messageContent = "Kính gửi " + user.getUserName() + ",\n" +
                    "\n" +
                    "Chúng tôi xin thông báo rằng mật khẩu tài khoản của bạn đã được thay đổi thành công. Mật khẩu mới của bạn là: " + newPassword + ". Nếu bạn không yêu cầu thay đổi mật khẩu này, vui lòng liên hệ với chúng tôi ngay lập tức qua [ vufit2004@icloud.com ] để được hỗ trợ.\n" +
                    "\n" +
                    "Để bảo mật tài khoản của bạn, chúng tôi khuyên bạn nên thay đổi mật khẩu ngay khi có thể. Nếu bạn quên mật khẩu, vui lòng sử dụng tính năng \"Quên mật khẩu\" trên trang đăng nhập của chúng tôi.\n" +
                    "\n" +
                    "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!\n" +
                    "\n" +
                    "Trân trọng,\n" +
                    "[Đội ngũ hỗ trợ]\n" +
                    "[Thông tin liên hệ]";

            try {
                serviceUser.sendEmail(email,subject,messageContent);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }





    }



}