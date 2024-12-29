package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import models.Payment;
public class PaymentDAO extends BaseDAO {

    public boolean createPayment(Payment payment) {
        String sql = "INSERT INTO payments (idUser, idOrder, methodPayment) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getIdUser());
            stmt.setInt(2, payment.getIdOrder());
            stmt.setString(3, payment.getMethodPayment());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Payment getPaymentById(int id) {
        String sql = "SELECT * FROM payments WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Payment(
                        rs.getInt("id"),
                        rs.getInt("idUser"),
                        rs.getInt("idOrder"),
                        rs.getString("methodPayment")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("id"),
                        rs.getInt("idUser"),
                        rs.getInt("idOrder"),
                        rs.getString("methodPayment")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public boolean updatePayment(Payment payment) {
        String sql = "UPDATE payments SET idUser = ?, idOrder = ?, methodPayment = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getIdUser());
            stmt.setInt(2, payment.getIdOrder());
            stmt.setString(3, payment.getMethodPayment());
            stmt.setInt(4, payment.getId());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePayment(int id) {
        String sql = "DELETE FROM payments WHERE id = ?";
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

