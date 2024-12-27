package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.TransactionHistory;

public class TransactionHistoryDAO extends BaseDAO {

    public boolean createTransactionHistory(TransactionHistory transactionHistory) {
        String sql = "INSERT INTO transactionHistory (transactionDate, totalPrice, idOrder, idUser, paymentMethod, shippingAddress) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new Date(transactionHistory.getTransactionDate().getTime()));
            stmt.setFloat(2, transactionHistory.getTotalPrice());
            stmt.setInt(3, transactionHistory.getIdOrder());
            stmt.setInt(4, transactionHistory.getIdUser());
            stmt.setString(5, transactionHistory.getPaymentMethod());
            stmt.setString(6, transactionHistory.getShippingAddress());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TransactionHistory getTransactionHistoryById(int id) {
        String sql = "SELECT * FROM transactionHistory WHERE idHistory = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TransactionHistory(
                        rs.getInt("idHistory"),
                        rs.getDate("transactionDate"),
                        rs.getFloat("totalPrice"),
                        rs.getInt("idOrder"),
                        rs.getInt("idUser"),
                        rs.getString("paymentMethod"),
                        rs.getString("shippingAddress")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TransactionHistory> getAllTransactionHistories() {
        List<TransactionHistory> transactionHistories = new ArrayList<>();
        String sql = "SELECT * FROM transactionHistory";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                transactionHistories.add(new TransactionHistory(
                        rs.getInt("idHistory"),
                        rs.getDate("transactionDate"),
                        rs.getFloat("totalPrice"),
                        rs.getInt("idOrder"),
                        rs.getInt("idUser"),
                        rs.getString("paymentMethod"),
                        rs.getString("shippingAddress")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionHistories;
    }

    public boolean updateTransactionHistory(TransactionHistory transactionHistory) {
        String sql = "UPDATE transactionHistory SET transactionDate = ?, totalPrice = ?, idOrder = ?, idUser = ?, paymentMethod = ?, shippingAddress = ? WHERE idHistory = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new Date(transactionHistory.getTransactionDate().getTime()));
            stmt.setFloat(2, transactionHistory.getTotalPrice());
            stmt.setInt(3, transactionHistory.getIdOrder());
            stmt.setInt(4, transactionHistory.getIdUser());
            stmt.setString(5, transactionHistory.getPaymentMethod());
            stmt.setString(6, transactionHistory.getShippingAddress());
            stmt.setInt(7, transactionHistory.getIdHistory());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTransactionHistory(int id) {
        String sql = "DELETE FROM transactionHistory WHERE idHistory = ?";
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

