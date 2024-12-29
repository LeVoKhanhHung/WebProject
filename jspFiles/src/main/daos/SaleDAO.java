package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Sale;

public class SaleDAO extends BaseDAO {

    public boolean createSale(Sale sale) {
        String sql = "INSERT INTO sales (idVariant, salePercent, saleStartDate, saleEndDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sale.getIdVariant());
            stmt.setInt(2, sale.getSalePercent());
            stmt.setTimestamp(3, new Timestamp(sale.getSaleStartDate().getTime()));
            stmt.setTimestamp(4, new Timestamp(sale.getSaleEndDate().getTime()));

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Sale getSaleById(int id) {
        String sql = "SELECT * FROM sales WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Sale(
                        rs.getInt("id"),
                        rs.getInt("idVariant"),
                        rs.getInt("salePercent"),
                        rs.getTimestamp("saleStartDate"),
                        rs.getTimestamp("saleEndDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sales.add(new Sale(
                        rs.getInt("id"),
                        rs.getInt("idVariant"),
                        rs.getInt("salePercent"),
                        rs.getTimestamp("saleStartDate"),
                        rs.getTimestamp("saleEndDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    public boolean updateSale(Sale sale) {
        String sql = "UPDATE sales SET idVariant = ?, salePercent = ?, saleStartDate = ?, saleEndDate = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sale.getIdVariant());
            stmt.setInt(2, sale.getSalePercent());
            stmt.setTimestamp(3, new Timestamp(sale.getSaleStartDate().getTime()));
            stmt.setTimestamp(4, new Timestamp(sale.getSaleEndDate().getTime()));
            stmt.setInt(5, sale.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSale(int id) {
        String sql = "DELETE FROM sales WHERE id = ?";
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
