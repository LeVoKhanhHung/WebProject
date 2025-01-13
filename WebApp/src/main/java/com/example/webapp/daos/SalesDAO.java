package com.example.webapp.daos;

import com.example.webapp.db.DBConnection;
import com.example.webapp.models.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {

    public static List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales";

        try (Connection conn = (Connection) DBConnection.get();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                sales.add(new Sale(
                        rs.getInt("id"),
                        rs.getInt("idVariant"),
                        rs.getInt("salePercent"),
                        rs.getDate("saleStartDate"),
                        rs.getDate("saleEndDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    public static boolean addSale(Sale sale) {
        String sql = "INSERT INTO sales (idVariant, salePercent, saleStartDate, saleEndDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sale.getIdVariant());
            pstmt.setInt(2, sale.getSalePercent());
            pstmt.setDate(3, new java.sql.Date(sale.getSaleStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(sale.getSaleEndDate().getTime()));

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateSale(Sale sale) {
        String sql = "UPDATE sales SET idVariant = ?, salePercent = ?, saleStartDate = ?, saleEndDate = ? WHERE id = ?";

        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sale.getIdVariant());
            pstmt.setInt(2, sale.getSalePercent());
            pstmt.setDate(3, new java.sql.Date(sale.getSaleStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(sale.getSaleEndDate().getTime()));
            pstmt.setInt(5, sale.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteSale(int id) {
        String sql = "DELETE FROM sales WHERE id = ?";

        try (Connection conn = (Connection) DBConnection.get();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
