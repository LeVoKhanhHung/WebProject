package com.example.webapp.daos;

import com.example.webapp.db.DBConnection;
import com.example.webapp.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static Connection connection;

    public ProductDAO() {
        this.connection = (Connection) DBConnection.get();
    }

    // Lấy danh sách sản phẩm
    public static List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("productName"),
                        rs.getInt("idCategory"),
                        rs.getInt("idSupplier"),
                        rs.getBoolean("isActive")
                );
                products.add(product);
            }
        }
        return products;
    }

    // Thêm sản phẩm
    public static boolean addProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (productName, idCategory, idSupplier, isActive) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getIdCategory());
            pstmt.setInt(3, product.getIdSupplier());
            pstmt.setBoolean(4, product.isActive());
            return pstmt.executeUpdate() > 0;
        }
    }

    // Cập nhật sản phẩm
    public static boolean updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET productName = ?, idCategory = ?, idSupplier = ?, isActive = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getIdCategory());
            pstmt.setInt(3, product.getIdSupplier());
            pstmt.setBoolean(4, product.isActive());
            pstmt.setInt(5, product.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    // Xóa sản phẩm
    public static boolean deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public static Product getProductById(int id) throws SQLException {
        Product product = null;
        String query = "SELECT p.id, p.productName, p.idCategory, p.idSupplier, p.isActive " +
                "FROM products p WHERE p.id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product(
                            rs.getInt("id"),
                            rs.getString("productName"),
                            rs.getInt("idCategory"),
                            rs.getInt("idSupplier"),
                            rs.getBoolean("isActive")
                    );
                }
            }
        }
        return product;
    }
}


