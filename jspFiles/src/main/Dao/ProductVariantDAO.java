package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.ProductVariant;

public class ProductVariantDAO extends BaseDAO {

    public boolean createProductVariant(ProductVariant productVariant) {
        String sql = "INSERT INTO product_variants (idProduct, weight, price, isActive, productDescription, importDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productVariant.getIdProduct());
            stmt.setFloat(2, productVariant.getWeight());
            stmt.setInt(3, productVariant.getPrice());
            stmt.setBoolean(4, productVariant.isActive());
            stmt.setString(5, productVariant.getProductDescription());
            stmt.setTimestamp(6, new Timestamp(productVariant.getImportDate().getTime()));

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ProductVariant getProductVariantById(int id) {
        String sql = "SELECT * FROM product_variants WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ProductVariant(
                        rs.getInt("id"),
                        rs.getInt("idProduct"),
                        rs.getFloat("weight"),
                        rs.getInt("price"),
                        rs.getBoolean("isActive"),
                        rs.getString("productDescription"),
                        rs.getTimestamp("importDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductVariant> getAllProductVariants() {
        List<ProductVariant> variants = new ArrayList<>();
        String sql = "SELECT * FROM product_variants";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                variants.add(new ProductVariant(
                        rs.getInt("id"),
                        rs.getInt("idProduct"),
                        rs.getFloat("weight"),
                        rs.getInt("price"),
                        rs.getBoolean("isActive"),
                        rs.getString("productDescription"),
                        rs.getTimestamp("importDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return variants;
    }

    public boolean updateProductVariant(ProductVariant productVariant) {
        String sql = "UPDATE product_variants SET idProduct = ?, weight = ?, price = ?, isActive = ?, productDescription = ?, importDate = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productVariant.getIdProduct());
            stmt.setFloat(2, productVariant.getWeight());
            stmt.setInt(3, productVariant.getPrice());
            stmt.setBoolean(4, productVariant.isActive());
            stmt.setString(5, productVariant.getProductDescription());
            stmt.setTimestamp(6, new Timestamp(productVariant.getImportDate().getTime()));
            stmt.setInt(7, productVariant.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProductVariant(int id) {
        String sql = "DELETE FROM product_variants WHERE id = ?";
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

