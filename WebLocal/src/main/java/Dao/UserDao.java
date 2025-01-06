package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    ConnDB dao = new ConnDB();
    public int check(String email, String pass) throws SQLException {

        int userId = -1;
        String query = "SELECT id FROM users WHERE email = ? AND userPassword = ? AND isActive = 1";
        PreparedStatement stmt = dao.conn.prepareStatement(query) ;

        stmt.setString(1, email);
        stmt.setString(2, pass);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            userId = rs.getInt("id"); // Lấy id của người dùng
        }
        return userId;
    }
    public int getUserIdByPhoneNumber(String phoneNumber) throws Exception {
        String query = "SELECT id FROM users WHERE phoneNumber = ?";
        int userId = -1;

        try (PreparedStatement stmt = dao.conn.prepareStatement(query)) {
            // Thiết lập giá trị cho tham số trong câu truy vấn
            stmt.setString(1, phoneNumber);

            // Thực thi truy vấn
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi truy vấn id user: " + e.getMessage());
        }

        return userId;
    }

}
