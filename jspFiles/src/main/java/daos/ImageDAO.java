package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO extends BaseDAO {

    public boolean addImageToProduct(int idProduct, byte[] imageData) {
        String sql = "INSERT INTO images (idProduct, imageData) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduct);
            stmt.setBytes(2, imageData);

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<byte[]> getImagesByProductId(int idProduct) {
        List<byte[]> images = new ArrayList<>();
        String sql = "SELECT imageData FROM images WHERE idProduct = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduct);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                images.add(rs.getBytes("imageData"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public boolean deleteImageByProductId(int idProduct) {
        String sql = "DELETE FROM images WHERE idProduct = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduct);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

