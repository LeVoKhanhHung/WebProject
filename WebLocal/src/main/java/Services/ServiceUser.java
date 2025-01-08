package Services;

import Dao.ConnDB;
import Dao.UserDao;
import Models.User.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class ServiceUser {
    ConnDB dao = new ConnDB();
    UserDao userDao = new UserDao();

    public int check(String email, String pass) throws SQLException {
      return userDao.check(email, pass);
    }
    public int getUserIdByPhoneNumber(String phoneNumber) throws Exception {

        return userDao.getUserIdByPhoneNumber(phoneNumber);
    }
    public boolean createUser(User user) {

        return userDao.createUser(user);
    }

    public User getUserById(int id) {

        return userDao.getUserById(id);
    }

    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    public  boolean updateUser(User user) {

        return userDao.updateUser(user);
    }

    public boolean deleteUser(int id) {

        return userDao.deleteUser(id);
    }

    public boolean registerUser(User user) {

        return userDao.registerUser(user);
    }


    public User getUserByEmail(String email) {

        return userDao.getUserByEmail(email);
    }

    public boolean updatePassword(int userId, String hashedPassword) {

        return userDao.updatePassword(userId, hashedPassword);
    }

    public  String hashPassword(String password) throws NoSuchAlgorithmException {
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
    public String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, 10);
    }
    public boolean checkCredentials(String email, String password) {
        return userDao.checkCredentials(email,password);
    }

    // Gửi email
    public boolean sendEmail(String recipientEmail, String subject, String messageContent) throws MessagingException {
        // Cấu hình thông tin SMTP
        final String senderEmail = "hoangvu03102004@gmail.com"; // Email của bạn
        final String senderPassword = "zybg xrit btjb ozow"; // Mật khẩu ứng dụng
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
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Tạo nội dung email
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(senderEmail)); // Địa chỉ email gửi
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail) // Địa chỉ email nhận
            );
            message.setSubject(subject);
            message.setText(messageContent);


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
    public static void main(String[] args) throws SQLException {
        ServiceUser s  = new ServiceUser();
        System.out.println(s.check("jane.doe@example.com","password456"));




















    }
}
