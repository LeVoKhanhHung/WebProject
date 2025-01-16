package com.example.webapp.daos;

import com.example.webapp.models.user.User;
import com.example.webapp.db.DBConnection;
import com.example.webapp.models.user.UserAdmin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean registerUser(User user) {
        boolean isRegistered = false;
        String sql = "INSERT INTO users (email, userPassword, userName, phoneNumber, birthDate, companyName, address, image, point, idFavoriteProduct, idRole, createDate, isActive) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = (Connection) DBConnection.get();
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
            stmt.setDate(12, new java.sql.Date(user.getCreateDate().getTime()));
            stmt.setBoolean(13, user.isActive());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isRegistered = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRegistered;
    }

    // Lấy danh sách tất cả người dùng
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
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
                        rs.getDate("createDate"),
                        rs.getBoolean("isActive")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Lấy thông tin người dùng cho trang Admin
    public List<UserAdmin> getAllUsersAdmin() {
        List<UserAdmin> userAdminList = new ArrayList<>();
        String query = "SELECT id, userName, email, isActive FROM users";

        try (Connection connection = DBConnection.get();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UserAdmin userAdmin = new UserAdmin();
                userAdmin.setId(resultSet.getInt("id"));
                userAdmin.setUserName(resultSet.getString("userName"));
                userAdmin.setEmail(resultSet.getString("email"));
                userAdmin.setActive(resultSet.getInt("isActive"));
                userAdminList.add(userAdmin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userAdminList;
    }

    // Lấy thông tin người dùng theo ID
    public User getUserById(int id) {
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
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
                            rs.getDate("createDate"),
                            rs.getBoolean("isActive")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Thêm người dùng mới
    public boolean addUser(User user) {
        String query = "INSERT INTO users (email, userPassword, userName, phoneNumber, birthDate, companyName, address, image, point, idFavoriteProduct, idRole, createDate, isActive) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query)) {
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
            stmt.setDate(12, new java.sql.Date(user.getCreateDate().getTime()));
            stmt.setBoolean(13, user.isActive());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin người dùng
    public boolean updateUser(User user) {
        String query = "UPDATE users SET email = ?, userPassword = ?, userName = ?, phoneNumber = ?, birthDate = ?, companyName = ?, address = ?, image = ?, point = ?, " +
                "idFavoriteProduct = ?, idRole = ?, createDate = ?, isActive = ? WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query)) {
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
            stmt.setDate(12, new java.sql.Date(user.getCreateDate().getTime()));
            stmt.setBoolean(13, user.isActive());
            stmt.setInt(14, user.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật trạng thái người dùng
    public boolean updateUserStatus(int userId, boolean isActive) {
        String query = "UPDATE users SET isActive = ? WHERE id = ?";

        try (Connection connection = DBConnection.get();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, isActive);
            statement.setInt(2, userId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Xóa người dùng
    public boolean deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User checkLogin(String email, String password) {
        User user = null;
        String query = "SELECT id, email, userName, idRole, userPassword FROM users WHERE email = ? AND isActive = 1";

        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            if (conn == null) {
                System.out.println("Kết nối cơ sở dữ liệu không thành công!");
                return null;
            }

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("userPassword");
                // So sánh mật khẩu người dùng nhập vào với mật khẩu trong cơ sở dữ liệu
                if (storedPassword.equals(password)) {
                    // Nếu đúng mật khẩu, trả về đối tượng User
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUserName(rs.getString("userName"));
                    user.setIdRole(rs.getInt("idRole"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void updateUserInfo(int userId, String userName, String userEmail, String userStatus) {
    }
}
