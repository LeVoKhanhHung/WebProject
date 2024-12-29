package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Product;

public class ProductDAO extends BaseDAO {

    public boolean createProduct(Product product) {
        String sql = "INSERT INTO products (productName, idCategory, idSupplier, isActive) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getIdCategory());
            stmt.setInt(3, product.getIdSupplier());
            stmt.setBoolean(4, product.isActive());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("productName"),
                        rs.getInt("idCategory"),
                        rs.getInt("idSupplier"),
                        rs.getBoolean("isActive")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("productName"),
                        rs.getInt("idCategory"),
                        rs.getInt("idSupplier"),
                        rs.getBoolean("isActive")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET productName = ?, idCategory = ?, idSupplier = ?, isActive = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getIdCategory());
            stmt.setInt(3, product.getIdSupplier());
            stmt.setBoolean(4, product.isActive());
            stmt.setInt(5, product.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
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

