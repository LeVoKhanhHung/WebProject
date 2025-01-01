// SupplierDAO.java
package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Supplier;

public class SupplierDAO extends BaseDAO {

    public boolean createSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers (supplierName, contactInfo, address, isActive) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supplier.getSupplierName());
            stmt.setString(2, supplier.getContactInfo());
            stmt.setString(3, supplier.getAddress());
            stmt.setBoolean(4, supplier.isActive());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Supplier getSupplierById(int id) {
        String sql = "SELECT * FROM suppliers WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Supplier(
                        rs.getInt("id"),
                        rs.getString("supplierName"),
                        rs.getString("contactInfo"),
                        rs.getString("address"),
                        rs.getBoolean("isActive")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getInt("id"),
                        rs.getString("supplierName"),
                        rs.getString("contactInfo"),
                        rs.getString("address"),
                        rs.getBoolean("isActive")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public boolean updateSupplier(Supplier supplier) {
        String sql = "UPDATE suppliers SET supplierName = ?, contactInfo = ?, address = ?, isActive = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, supplier.getSupplierName());
            stmt.setString(2, supplier.getContactInfo());
            stmt.setString(3, supplier.getAddress());
            stmt.setBoolean(4, supplier.isActive());
            stmt.setInt(5, supplier.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSupplier(int id) {
        String sql = "DELETE FROM suppliers WHERE id = ?";
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
