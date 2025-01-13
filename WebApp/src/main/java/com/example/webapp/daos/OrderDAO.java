package com.example.webapp.daos;

import com.example.webapp.db.DBConnection;
import com.example.webapp.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public static List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT o.id, o.idUser, o.totalPrice, o.isPaid, o.createDate, o.receiveDate, " +
                "o.receiveAddress, o.isActive, o.idShipping " +
                "FROM orders o " +
                "JOIN users u ON o.idUser = u.id " +
                "JOIN shippings s ON o.idShipping = s.id";

        try (Connection conn = (Connection) DBConnection.get();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("idUser"),
                        rs.getInt("totalPrice"),
                        rs.getBoolean("isPaid"),
                        rs.getTimestamp("createDate"),
                        rs.getTimestamp("receiveDate"),
                        rs.getString("receiveAddress"),
                        rs.getBoolean("isActive"),
                        rs.getInt("idShipping")
                ));
            }
        }
        return orders;
    }

    public static boolean updateOrderStatus(int orderId, String status) throws SQLException {
        String query = "UPDATE orders SET isActive = ? WHERE id = ?";
        boolean statusUpdate = status.equals("active");
        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setBoolean(1, statusUpdate);
            pstmt.setInt(2, orderId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public static boolean deleteOrder(int orderId) throws SQLException {
        String query = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
