package com.example.webapp.daos;

import com.example.webapp.db.DBConnection;
import com.example.webapp.models.Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO {
    public static List<Rating> getAllRatings() throws SQLException {
        List<Rating> ratings = new ArrayList<>();
        String query = "SELECT r.id, r.idProduct, r.idUser, r.ratingRank, r.comment, r.createDate, r.status " +
                "FROM ratings r " +
                "JOIN users u ON r.idUser = u.id " +
                "JOIN products p ON r.idProduct = p.id";

        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Rating rating = new Rating(
                        rs.getInt("id"),
                        rs.getInt("idProduct"),
                        rs.getInt("idUser"),
                        rs.getInt("ratingRank"),
                        rs.getString("comment"),
                        rs.getTimestamp("createDate"),
                        rs.getInt("status")
                );
                ratings.add(rating);
            }
        }
        return ratings;
    }

    public static boolean deleteRating(int ratingId) throws SQLException {
        String query = "DELETE FROM ratings WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ratingId);
            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean respondToRating(int ratingId, String responseText) throws SQLException {
        String query = "UPDATE ratings SET adminResponse = ? WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, responseText);
            stmt.setInt(2, ratingId);
            return stmt.executeUpdate() > 0;
        }
    }
}
