package Services;

import Dao.ProductsDao;
import Models.Productt;
import Models.cart.CartProduct;
import Models.cart.Cart;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ServiceProduct {
    List<Productt> productList = new ArrayList<>();
    private ProductsDao dao = new ProductsDao();

    public List<Productt> getProductList(double weight) throws SQLException {
        List<Productt> productList = new ArrayList<>(); // Khởi tạo danh sách sản phẩm

        // Câu SQL với tham số "?"
        PreparedStatement ps = dao.conn.prepareStatement(
                "SELECT " +
                        "    p.id AS productID, " +
                        "    p.productName, " +
                        "    i.imageData AS productImage, " +
                        "    pv.weight AS productWeight, " +
                        "    pv.quantity AS productQuantity, " +
                        "    pv.price AS productPrice, " +
                        "    (pv.price * pv.quantity) AS totalPrice, " +  // Dấu phẩy thiếu
                        "    s.salePercent " +                          // Dòng này tiếp nối
                        "FROM " +
                        "    products p " +
                        "JOIN " +
                        "    product_variants pv ON p.id = pv.idProduct " +
                        "LEFT JOIN " +
                        "    Images i ON p.id = i.idProduct " +
                        "INNER JOIN " +
                        "    sales s ON s.idVariant = pv.id " +
                        "WHERE " +
                        "    pv.weight = ?;"
        );


// Truyền giá trị "weight" vào tham số ?
        ps.setDouble(1, weight);

// Thực thi câu lệnh SQL
        ResultSet rs = ps.executeQuery();

// Tạo danh sách Product để lưu kết quả


// Xử lý kết quả trả về từ ResultSet
        while (rs.next()) {
            // Lấy các giá trị từ ResultSet
            String productID = rs.getString("productID"); // id của sản phẩm
            String productName = rs.getString("productName"); // tên sản phẩm
            String productImage = rs.getString("productImage"); // ảnh sản phẩm
            int productWeight = rs.getInt("productWeight"); // trọng lượng sản phẩm
            int productQuantity = rs.getInt("productQuantity"); // số lượng sản phẩm
            double productPrice = rs.getDouble("productPrice"); // giá sản phẩm
            double totalPrice = rs.getDouble("totalPrice"); // tổng tiền sản phẩm (giá * số lượng)
            double salePercent = rs.getDouble("salePercent");

            // Tạo đối tượng Product và thêm vào danh sách
            Productt product = new Productt(
                    productID,           // id
                    productName,         // tên sản phẩm
                    productQuantity,     // số lượng
                    productImage,        // ảnh sản phẩm
                    productWeight,       // trọng lượng
                    productPrice,        // giá sản phẩm
                    totalPrice,     // tổng tiền,
                    salePercent
            );

            productList.add(product);
        }




        return productList;
    }
    public Productt getById(String id, int weight) throws SQLException {
        List<Productt> result = getProductList(weight);

        for (Productt pro:
                result) {

            if(pro.getId().equals(id) &&  pro.weight == weight  ){

                return pro;
            }
        }
        System.out.println("San pham khong ton tai");
        return null;
    }
    public int getProductVariantCountByIdAndWeight(int productId, float weight) {
        String query = "SELECT SUM(quantity) FROM product_variants WHERE idProduct = ? AND weight = ?";
        try (PreparedStatement statement = dao.conn.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.setFloat(2, weight);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // trả về số lượng bản ghi phù hợp
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // trả về 0 nếu không tìm thấy bản ghi nào
    }
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
    public int getUserIdByPhoneNumber(String phoneNumber) throws Exception {
        String query = "SELECT id FROM users WHERE phoneNumber = ?";
        int userId = -1;

        try (PreparedStatement stmt = dao.conn.prepareStatement(query)) {
            // Thiết lập giá trị cho tham số trong câu truy vấn
            stmt.setString(1, phoneNumber);

            // Thực thi truy vấn
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi truy vấn id user: " + e.getMessage());
        }

        return userId;
    }
    public int saveTransactionHistory(int userId, int orderId, double totalPrice, String paymentMethod, String shippingAddress) throws SQLException {
        String sql = "INSERT INTO transactionHistory (transactionDate, totalPrice, idOrder, idUser, paymentMethod, shippingAddress) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = dao.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Lấy transactionDate = createDate + 4 ngày
            String transactionDate = getReceiveDate(userId, orderId);
            if (transactionDate == null) {
                throw new SQLException("Không tìm thấy ngày tạo đơn hàng!");
            }

            // Gán các giá trị cho PreparedStatement
            stmt.setDate(1, Date.valueOf(transactionDate));
            stmt.setDouble(2, totalPrice);
            stmt.setInt(3, orderId);
            stmt.setInt(4, userId);
            stmt.setString(5, paymentMethod);
            stmt.setString(6, shippingAddress);

            stmt.executeUpdate();

            // Lấy ID của giao dịch vừa được tạo
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Trả về ID giao dịch
            }
        }
        return -1;
    }
    // Ngay dua vao lich su laf +4 ngay
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
    // Lay ra paymentMethod
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
    public boolean insertPayment(int idUser, int idOrder, String methodPayment) throws SQLException {
        String sql = "INSERT INTO Payment (idUser, idOrder, methodPayment) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = dao.conn.prepareStatement(sql);

            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idOrder);
            preparedStatement.setString(3, methodPayment);
        int rowsInserted = preparedStatement.executeUpdate();
        return rowsInserted > 0;

    }



    public static void main(String[] args) throws Exception {
        ServiceProduct s = new ServiceProduct();
      //  System.out.println(s.getProductVariantCountByIdAndWeight(1,200));
        //System.out.println(s.getProductList(22));
       // System.out.println(s.getById("1",200));
        System.out.println(s.getUserIdByPhoneNumber("0912345678"));
       System.out.println(s.insertPayment(Integer.parseInt("2"),Integer.parseInt("17"),"COD"));

    }
}



