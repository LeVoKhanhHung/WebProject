package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Feedback;

public class FeedbackDAO extends BaseDAO {

    public boolean createFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedbacks (idCustomer, comment, createDate) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getIdCustomer());
            stmt.setString(2, feedback.getComment());
            stmt.setTimestamp(3, new Timestamp(feedback.getCreateDate().getTime()));

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Feedback getFeedbackById(int id) {
        String sql = "SELECT * FROM feedbacks WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Feedback(
                        rs.getInt("id"),
                        rs.getInt("idCustomer"),
                        rs.getString("comment"),
                        rs.getTimestamp("createDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Feedback> getFeedbacksByCustomerId(int idCustomer) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM feedbacks WHERE idCustomer = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCustomer);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                feedbacks.add(new Feedback(
                        rs.getInt("id"),
                        rs.getInt("idCustomer"),
                        rs.getString("comment"),
                        rs.getTimestamp("createDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    public boolean updateFeedback(Feedback feedback) {
        String sql = "UPDATE feedbacks SET idCustomer = ?, comment = ?, createDate = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getIdCustomer());
            stmt.setString(2, feedback.getComment());
            stmt.setTimestamp(3, new Timestamp(feedback.getCreateDate().getTime()));
            stmt.setInt(4, feedback.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFeedback(int id) {
        String sql = "DELETE FROM feedbacks WHERE id = ?";
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
