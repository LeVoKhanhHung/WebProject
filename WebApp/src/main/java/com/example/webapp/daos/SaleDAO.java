package com.example.webapp.daos;

import com.example.webapp.db.DBConnection;
import com.example.webapp.models.Sale;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class SaleDAO {

    // Lấy danh sách tất cả chương trình khuyến mãi
    public List<Sale> getAllSales() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales"; // Câu truy vấn SQL phù hợp
        try (Connection connection = DBConnection.get();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Sale sale = new Sale(
                        rs.getInt("id"),
                        rs.getInt("idVariant"),
                        rs.getInt("salePercent"),
                        rs.getTimestamp("saleStartDate"),
                        rs.getTimestamp("saleEndDate")
                );
                sales.add(sale);
            }
        }
        return sales;
    }

    // Thêm chương trình khuyến mãi mới
    public boolean addSale(Sale sale) throws SQLException {
        String query = "INSERT INTO sales (idVariant, salePercent, saleStartDate, saleEndDate) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.get();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, sale.getIdVariant());
            ps.setInt(2, sale.getSalePercent());
            ps.setTimestamp(3, sale.getSaleStartDate());
            ps.setTimestamp(4, sale.getSaleEndDate());

            return ps.executeUpdate() > 0;
        }
    }

    // Cập nhật chương trình khuyến mãi
    public boolean updateSale(Sale sale) throws SQLException {
        String query = "UPDATE sales SET idVariant = ?, salePercent = ?, saleStartDate = ?, saleEndDate = ? WHERE id = ?";

        try (Connection connection = DBConnection.get();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, sale.getIdVariant());
            ps.setInt(2, sale.getSalePercent());
            ps.setTimestamp(3, sale.getSaleStartDate());
            ps.setTimestamp(4, sale.getSaleEndDate());
            ps.setInt(5, sale.getId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteSale(int id) throws SQLException {
        String query = "DELETE FROM sales WHERE id = ?";

        try (Connection connection = DBConnection.get();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}
