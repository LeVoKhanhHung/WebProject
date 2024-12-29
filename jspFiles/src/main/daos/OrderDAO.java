package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Order;

public class OrderDAO extends BaseDAO {

    public boolean createOrder(Order order) {
        String sql = "INSERT INTO orders (idUser, totalPrice, isPaid, createDate, receiveDate, receiveAddress, isActive, idShipping) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, order.getIdUser());
            stmt.setInt(2, order.getTotalPrice());
            stmt.setBoolean(3, order.isPaid());
            stmt.setTimestamp(4, new Timestamp(order.getCreateDate().getTime()));
            stmt.setTimestamp(5, new Timestamp(order.getReceiveDate().getTime()));
            stmt.setString(6, order.getReceiveAddress());
            stmt.setBoolean(7, order.isActive());
            stmt.setInt(8, order.getIdShipping());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Order getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        rs.getInt("idUser"),
                        rs.getInt("totalPrice"),
                        rs.getBoolean("isPaid"),
                        rs.getTimestamp("createDate"),
                        rs.getTimestamp("receiveDate"),
                        rs.getString("receiveAddress"),
                        rs.getBoolean("isActive"),
                        rs.getInt("idShipping")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("idUser"),
                        rs.getInt("totalPrice"),
                        rs.getBoolean("isPaid"),
                        rs.getTimestamp("createDate"),
                        rs.getTimestamp("receiveDate"),
                        rs.getString("receiveAddress"),
                        rs.getBoolean("isActive"),
                        rs.getInt("idShipping")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

        public boolean updateOrder(Order order) {
            String sql = "UPDATE orders SET idUser = ?, totalPrice = ?, isPaid = ?, createDate = ?, receiveDate = ?, receiveAddress = ?, isActive = ?, idShipping = ? WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, order.getIdUser());
                stmt.setInt(2, order.getTotalPrice());
                stmt.setBoolean(3, order.isPaid());
                stmt.setTimestamp(4, new Timestamp(order.getCreateDate().getTime()));
                stmt.setTimestamp(5, new Timestamp(order.getReceiveDate().getTime()));
                stmt.setString(6, order.getReceiveAddress());
                stmt.setBoolean(7, order.isActive());
                stmt.setInt(8, order.getIdShipping());
                stmt.setInt(9, order.getId());

                int result = stmt.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

    public static boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
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

