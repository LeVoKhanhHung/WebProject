package Dao;

import Models.User.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public int check(String email, String pass) throws SQLException {
        ConnDB dao = new ConnDB();
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
    public boolean checkCredentials(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND userPassword = ?";
        ConnDB dao = new ConnDB();
        try (Connection conn = dao.getConn();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true; // Nếu có kết quả, nghĩa là login hợp lệ
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Không tìm thấy người dùng hoặc mật khẩu không hợp lệ
    }

    public int getUserIdByPhoneNumber(String phoneNumber) throws Exception {
        ConnDB dao = new ConnDB();
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
    public boolean createUser(User user) {
        ConnDB dao = new ConnDB();
        String sql = "INSERT INTO users (email, userPassword, userName, phoneNumber, birthDate, companyName, address, image, point, idFavoriteProduct, idRole, createDate, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUserPassword());
            stmt.setString(3, user.getUserName());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
            stmt.setString(6, user.getCompanyName());
            stmt.setString(7, user.getAddress());
            stmt.setString(8, user.getImage());
            stmt.setInt(9, user.getPoint());
            stmt.setInt(10, user.getIdFavoriteProduct());
            stmt.setInt(11, user.getIdRole());
            stmt.setTimestamp(12, new Timestamp(user.getCreateDate().getTime()));
            stmt.setBoolean(13, user.isActive());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int id) {
        ConnDB dao = new ConnDB();
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("userPassword"),
                        rs.getString("userName"),
                        rs.getString("phoneNumber"),
                        rs.getDate("birthDate"),
                        rs.getString("companyName"),
                        rs.getString("address"),
                        rs.getString("image"),
                        rs.getInt("point"),
                        rs.getInt("idFavoriteProduct"),
                        rs.getInt("idRole"),
                        rs.getTimestamp("createDate"),
                        rs.getBoolean("isActive")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        ConnDB dao = new ConnDB();
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = dao.getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("userPassword"),
                        rs.getString("userName"),
                        rs.getString("phoneNumber"),
                        rs.getDate("birthDate"),
                        rs.getString("companyName"),
                        rs.getString("address"),
                        rs.getString("image"),
                        rs.getInt("point"),
                        rs.getInt("idFavoriteProduct"),
                        rs.getInt("idRole"),
                        rs.getTimestamp("createDate"),
                        rs.getBoolean("isActive")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public  boolean updateUser(User user) {
        ConnDB dao = new ConnDB();
        String sql = "UPDATE users SET email = ?, userPassword = ?, userName = ?, phoneNumber = ?, birthDate = ?, companyName = ?, address = ?, image = ?, point = ?, idFavoriteProduct = ?, idRole = ?, createDate = ?, isActive = ? WHERE id = ?";
        try ( Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUserPassword());
            stmt.setString(3, user.getUserName());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
            stmt.setString(6, user.getCompanyName());
            stmt.setString(7, user.getAddress());
            stmt.setString(8, user.getImage());
            stmt.setInt(9, user.getPoint());
            stmt.setInt(10, user.getIdFavoriteProduct());
            stmt.setInt(11, user.getIdRole());
            stmt.setTimestamp(12, new Timestamp(user.getCreateDate().getTime()));
            stmt.setBoolean(13, user.isActive());
            stmt.setInt(14, user.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int id) {
        ConnDB dao = new ConnDB();
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(User user) {
        ConnDB dao = new ConnDB();
        String sql = "INSERT INTO users (email, userPassword, userName, createDate, isActive) VALUES (?, ?, ?, NOW(), true)";
        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUserPassword());
            stmt.setString(3, user.getUserName());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public User getUserByEmail(String email) {
        ConnDB dao = new ConnDB();
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setUserName(rs.getString("userName"));
                    user.setCreateDate(rs.getDate("createDate"));
                    user.setActive(rs.getBoolean("isActive"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(int userId, String hashedPassword) {
        ConnDB dao = new ConnDB();
        String sql = "UPDATE users SET userPassword = ? WHERE id = ?";
        try (Connection conn = dao.getConn();  // Mở kết nối
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hashedPassword);
            stmt.setInt(2, userId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    public static void main(String[] args) throws NoSuchAlgorithmException {
        UserDao s = new UserDao();
        System.out.println(s.hashPassword("hoangvu1234"));
    }
}
