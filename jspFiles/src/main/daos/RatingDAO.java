package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Rating;

public class RatingDAO extends BaseDAO {

    public boolean createRating(Rating rating) {
        String sql = "INSERT INTO ratings (idProduct, idUser, ratingRank, comment, createDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rating.getIdProduct());
            stmt.setInt(2, rating.getIdUser());
            stmt.setInt(3, rating.getRatingRank());
            stmt.setString(4, rating.getComment());
            stmt.setTimestamp(5, new Timestamp(rating.getCreateDate().getTime()));
            stmt.setInt(6, rating.getStatus());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Rating getRatingById(int id) {
        String sql = "SELECT * FROM ratings WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Rating(
                        rs.getInt("id"),
                        rs.getInt("idProduct"),
                        rs.getInt("idUser"),
                        rs.getInt("ratingRank"),
                        rs.getString("comment"),
                        rs.getTimestamp("createDate"),
                        rs.getInt("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Rating> getRatingsByProductId(int idProduct) {
        List<Rating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM ratings WHERE idProduct = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduct);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ratings.add(new Rating(
                        rs.getInt("id"),
                        rs.getInt("idProduct"),
                        rs.getInt("idUser"),
                        rs.getInt("ratingRank"),
                        rs.getString("comment"),
                        rs.getTimestamp("createDate"),
                        rs.getInt("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }

    public boolean updateRating(Rating rating) {
        String sql = "UPDATE ratings SET idProduct = ?, idUser = ?, ratingRank = ?, comment = ?, createDate = ?, status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rating.getIdProduct());
            stmt.setInt(2, rating.getIdUser());
            stmt.setInt(3, rating.getRatingRank());
            stmt.setString(4, rating.getComment());
            stmt.setTimestamp(5, new Timestamp(rating.getCreateDate().getTime()));
            stmt.setInt(6, rating.getStatus());
            stmt.setInt(7, rating.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRating(int id) {
        String sql = "DELETE FROM ratings WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
