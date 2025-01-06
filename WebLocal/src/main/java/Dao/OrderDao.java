package Dao;

import Models.cart.Cart;
import Models.cart.CartProduct;
import Models.inforTransaction.Product;
import Models.inforTransaction.Transaction;
import Models.inforTransaction.TransactionHistory;
import Services.ServiceProduct;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    ConnDB dao = new ConnDB();
    public int saveOrder(int userId, Double totalPrice, String receiveAddress, int shippingId) throws SQLException {
        String sql = "INSERT INTO orders (idUser, totalPrice, isPaid, createDate, receiveDate, receiveAddress, isActive, idShipping) " +
                "VALUES (?, ?, 0, NOW(), NULL, ?, 1, ?)";
        try (PreparedStatement stmt = dao.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.setDouble(2, totalPrice);
            stmt.setString(3, receiveAddress);
            stmt.setInt(4, 1);

            stmt.executeUpdate();

            // Lấy ID đơn hàng vừa được tạo
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Trả về ID đơn hàng
            }
        }

        return -1;
    }
    public void saveOrderDetails(int orderId, Cart cart) throws SQLException {
        System.out.println(cart.getItems());
        String sql = "INSERT INTO order_details (idOrder, idProduct, quantity, price,nameProduct) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement stmt = dao.conn.prepareStatement(sql)) {
            for (CartProduct item : cart.getItems()) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, Integer.parseInt(item.getId()));
                stmt.setInt(3, item.getQuantity());
                stmt.setDouble(4, item.getPrice());
                stmt.setString(5, item.getName());
                stmt.addBatch(); // Thêm vào batch
            }
            stmt.executeBatch(); // Thực hiện batch
        }
    }
    public List<Product> getOrderDetailsByIdOrder(int idOrder) throws SQLException {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM order_details WHERE idOrder = ?";


        PreparedStatement preparedStatement = dao.conn.prepareStatement(query);

        // Thiết lập giá trị cho tham số `idOrder`
        preparedStatement.setInt(1, idOrder);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Lấy thông tin từ kết quả truy vấn
                int id = resultSet.getInt("id");
                int idProduct = resultSet.getInt("idProduct");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("price");
                String nameProduct = resultSet.getString("nameProduct");

                // Tạo đối tượng Product và thêm vào danh sách
                products.add(new Product(id, idOrder, idProduct, quantity, price, nameProduct));
            }
        }
        return products;
    }

    public boolean insertPayment(int idUser, int idOrder, String methodPayment) throws SQLException {
        String sql = "INSERT INTO Payment (idUser, idOrder, methodPayment) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = dao.conn.prepareStatement(sql);

        preparedStatement.setInt(1, idUser);
        preparedStatement.setInt(2, idOrder);
        preparedStatement.setString(3, methodPayment);
        int rowsInserted = preparedStatement.executeUpdate();
        return rowsInserted > 0;

    }
    public String getReceiveDate(int idUser, int idOrder) throws SQLException {
        String sql = "SELECT createDate FROM orders WHERE idUser = ? AND id = ?";

        try (PreparedStatement stmt = dao.conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.setInt(2, idOrder);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Date createDate = rs.getDate("createDate");
                if (createDate != null) {
                    // Cộng thêm 4 ngày
                    LocalDate receiveDate = createDate.toLocalDate().plusDays(4);
                    return receiveDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Trả về chuỗi định dạng
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy dữ liệu
    }
    public String paymentMethod(String idUser, String idOrder) throws SQLException {
        String sql = "Select methodPayment from Payment where idUser = ? and idOrder = ?";
        String payment = "";
        try (PreparedStatement stmt = dao.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1,idUser);
            stmt.setString(2,idOrder);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    payment = rs.getString("methodPayment");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        // Lấy ID của giao dịch vừa được tạo

        return payment;

    }
}
