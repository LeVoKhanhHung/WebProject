// WishlistDAO.java
package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Wishlist;

public class WishlistDAO extends BaseDAO {

    public boolean addProductToWishlist(Wishlist wishlist) {
        String sql = "INSERT INTO wishlists (idUser, idProduct, addDate) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, wishlist.getIdUser());
            stmt.setInt(2, wishlist.getIdProduct());
            stmt.setDate(3, new java.sql.Date(wishlist.getAddDate().getTime())); // Chuyển đổi Date sang java.sql.Date

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Wishlist> getWishlistByUserId(int userId) {
        List<Wishlist> wishlists = new ArrayList<>();
        String sql = "SELECT * FROM wishlists WHERE idUser = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                wishlists.add(new Wishlist(
                        rs.getInt("id"),
                        rs.getInt("idUser"),
                        rs.getInt("idProduct"),
                        rs.getDate("addDate") // Lấy ngày thêm vào Wishlist
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlists;
    }

    public boolean removeProductFromWishlist(int id) {
        String sql = "DELETE FROM wishlists WHERE id = ?";
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
