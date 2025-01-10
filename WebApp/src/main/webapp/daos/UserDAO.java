package com.example.webapp.daos;

import com.example.webapp.models.User;
import com.example.webapp.db.DBConnection;
import java.sql.*;

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

    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try (Connection connection = (Connection) DBConnection.get()) {
            String sql = "SELECT * FROM users WHERE email = ? AND userPassword = ? AND isActive = true";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
