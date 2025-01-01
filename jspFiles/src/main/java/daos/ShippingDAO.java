package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Shipping;
public class ShippingDAO extends BaseDAO {

    public boolean createShipping(Shipping shipping) {
        String sql = "INSERT INTO shippings (name, shoppingFee) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shipping.getName());
            stmt.setFloat(2, shipping.getShippingFee()); // Đảm bảo shoppingFee được sử dụng đúng

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Shipping getShippingById(int id) {
        String sql = "SELECT * FROM shippings WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Shipping(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("shoppingFee") // Đảm bảo lấy shoppingFee từ cơ sở dữ liệu
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Shipping> getAllShippings() {
        List<Shipping> shippings = new ArrayList<>();
        String sql = "SELECT * FROM shippings";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                shippings.add(new Shipping(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("shoppingFee") // Đảm bảo lấy shoppingFee từ cơ sở dữ liệu
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shippings;
    }

    public boolean updateShipping(Shipping shipping) {
        String sql = "UPDATE shippings SET name = ?, shoppingFee = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shipping.getName());
            stmt.setFloat(2, shipping.getShippingFee()); // Đảm bảo shoppingFee được cập nhật
            stmt.setInt(3, shipping.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteShipping(int id) {
        String sql = "DELETE FROM shippings WHERE id = ?";
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
