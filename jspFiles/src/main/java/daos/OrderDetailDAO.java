package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.OrderDetail;

public class OrderDetailDAO extends BaseDAO {

    public boolean addOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO order_details (idOrder, idProduct, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDetail.getIdOrder());
            stmt.setInt(2, orderDetail.getIdProduct());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setInt(4, orderDetail.getPrice());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int idOrder) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM order_details WHERE idOrder = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrder);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderDetails.add(new OrderDetail(
                        rs.getInt("id"),
                        rs.getInt("idOrder"),
                        rs.getInt("idProduct"),
                        rs.getInt("quantity"),
                        rs.getInt("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public boolean updateOrderDetail(OrderDetail orderDetail) {
        String sql = "UPDATE order_details SET idProduct = ?, quantity = ?, price = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDetail.getIdProduct());
            stmt.setInt(2, orderDetail.getQuantity());
            stmt.setInt(3, orderDetail.getPrice());
            stmt.setInt(4, orderDetail.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteOrderDetail(int id) {
        String sql = "DELETE FROM order_details WHERE id = ?";
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

