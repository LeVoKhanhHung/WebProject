package com.example.webapp.daos;

import com.example.webapp.db.DBConnection;
import com.example.webapp.models.feedbacks.FeedbackAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    public List<FeedbackAdmin> getAllFeedbacksWithUserInfo() {
        List<FeedbackAdmin> feedbackList = new ArrayList<>();
        String sql = "SELECT f.id, f.idCustomer, f.comment, f.createDate, u.userName, u.email, u.phoneNumber " +
                "FROM feedbacks f " +
                "JOIN users u ON f.idCustomer = u.id";

        try (Connection connection = DBConnection.get();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FeedbackAdmin feedback = new FeedbackAdmin();
                feedback.setId(resultSet.getInt("id"));
                feedback.setIdCustomer(resultSet.getInt("idCustomer"));
                feedback.setComment(resultSet.getString("comment"));
                feedback.setCreateDate(resultSet.getTimestamp("createDate"));
                feedback.setCustomerName(resultSet.getString("userName"));
                feedback.setCustomerEmail(resultSet.getString("email"));
                feedback.setCustomerPhone(resultSet.getString("phoneNumber"));
                feedbackList.add(feedback);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

    public boolean saveResponse(int feedbackId, String responseMessage) {
        String sql = "UPDATE feedbacks SET adminResponse = ?, responseDate = NOW() WHERE id = ?";
        try (Connection connection = DBConnection.get();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, responseMessage);
            statement.setInt(2, feedbackId);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

