package com.example.webapp.daos;

import com.example.webapp.db.DBConnection;

import java.sql.*;
import java.util.*;

public class AdminDAO {
    // Lấy tổng số sản phẩm
    public int getTotalProducts() throws SQLException {
        int totalProducts = 0;
        String sql = "SELECT COUNT(*) FROM products WHERE isActive = 1";
        try (Connection conn = DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                totalProducts = rs.getInt(1);
            }
        }
        return totalProducts;
    }


    public int getOrdersToday() throws SQLException {
        int ordersToday = 0;
        String sql = "SELECT COUNT(*) FROM orders WHERE DATE(createDate) = CURDATE() AND isActive = 1";
        try (Connection conn = DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                ordersToday = rs.getInt(1);
            }
        }
        return ordersToday;
    }


    public int getNewFeedbacks() throws SQLException {
        int newFeedbacks = 0;
        String sql = "SELECT COUNT(*) FROM feedbacks WHERE DATE(createDate) = CURDATE()";
        try (Connection conn = DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                newFeedbacks = rs.getInt(1);
            }
        }
        return newFeedbacks;
    }

    // Lấy doanh thu theo tháng
    public List<Map<String, Object>> getAllRevenueData() {
        List<Map<String, Object>> revenues = new ArrayList<>();
        String query = "SELECT YEAR(order_date) AS year, MONTH(order_date) AS month, " +
                "SUM(total_price) AS totalRevenue, COUNT(id) AS orderCount " +
                "FROM orders GROUP BY YEAR(order_date), MONTH(order_date)";

        try (Connection conn = DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Tạo một Map để lưu trữ dữ liệu của từng tháng
                Map<String, Object> revenueData = new HashMap<>();
                String month = "Tháng " + rs.getInt("month");
                double totalRevenue = rs.getDouble("totalRevenue");
                int orderCount = rs.getInt("orderCount");

                revenueData.put("month", month);
                revenueData.put("totalRevenue", totalRevenue);
                revenueData.put("orderCount", orderCount);

                // Thêm vào danh sách kết quả
                revenues.add(revenueData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenues;
    }

    // Hàm main để kiểm tra
    public static void main(String[] args) throws SQLException {
        AdminDAO adminDAO = new AdminDAO();
        int totalProducts = adminDAO.getTotalProducts();
        System.out.println("Tổng số sản phẩm: " + totalProducts);
        int orderToday = adminDAO.getOrdersToday();
        System.out.println("Order Today: " + orderToday);
    }
}
