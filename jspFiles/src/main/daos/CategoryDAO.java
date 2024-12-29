package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Category;

public class CategoryDAO extends BaseDAO {

    public boolean createCategory(Category category) {
        String sql = "INSERT INTO categories (name, description, id_variants) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getIdVariants()); // Đảm bảo idVariant được đưa vào

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM categories WHERE idCategory = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Category(
                        rs.getInt("idCategory"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("id_variants") // Đảm bảo lấy id_variants từ cơ sở dữ liệu
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("idCategory"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("id_variants") // Đảm bảo lấy id_variants từ cơ sở dữ liệu
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public boolean updateCategory(Category category) {
        String sql = "UPDATE categories SET name = ?, description = ?, id_variants = ? WHERE idCategory = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getIdVariants()); // Đảm bảo idVariant được cập nhật
            stmt.setInt(4, category.getIdCategory());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM categories WHERE idCategory = ?";
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
