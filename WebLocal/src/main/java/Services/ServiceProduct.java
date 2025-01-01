package Services;

import Dao.ConnDB;
import Models.Product.ListProduct;
import Models.Productt;
import Models.cart.CartProduct;
import Models.cart.Cart;
import Models.inforTransaction.Product;
import Models.inforTransaction.Transaction;
import Models.inforTransaction.TransactionHistory;
import Models.Products.Products;

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
    private ConnDB dao = new ConnDB();

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
    public void selectTransactionHistory(int idUser,Transaction transaction) throws SQLException {
        ServiceProduct pro = new ServiceProduct();
        String query = "SELECT * FROM transactionHistory WHERE idUser = ?";


             PreparedStatement stmt = dao.conn.prepareStatement(query) ;

            stmt.setInt(1, idUser); // Thay idUser bằng giá trị thực tế
            // Thay idOrder bằng giá trị thực tế

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Xử lý dữ liệu trong kết quả
                int id = rs.getInt("idHistory");
                Date transactionDate = rs.getDate("transactionDate");
                int idOder = rs.getInt("idOrder");
                float totalPrice = rs.getFloat("totalPrice");
                String paymentMethod = rs.getString("paymentMethod");
                String shippingAddress = rs.getString("shippingAddress");
                List<Product> products = pro.getOrderDetailsByIdOrder(idOder);
                transaction.addTransactionHistory(new TransactionHistory(id,transactionDate,totalPrice,idOder,idUser,paymentMethod,shippingAddress,products));

                System.out.println("ID: " + id);
                System.out.println("IdOrder: " + idOder);
                System.out.println("Transaction Date: " + transactionDate);
                System.out.println("Total Price: " + totalPrice);
                System.out.println("Payment Method: " + paymentMethod);
                System.out.println("Shipping Address: " + shippingAddress);

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
public ListProduct getListProduct() throws SQLException {
    ListProduct list = new ListProduct();
    String query = """
            SELECT
                p.id AS ProductID,
                p.productName,
                MIN(pv.price) AS MinPrice,
                MAX(pv.price) AS MaxPrice,
                img1.imageData AS Image1,
                img2.imageData AS Image2
            FROM
                products p
            LEFT JOIN product_variants pv ON p.id = pv.idProduct
            LEFT JOIN (
                SELECT i1.idProduct, i1.imageData
                FROM Images i1
                WHERE (
                    SELECT COUNT(*) 
                    FROM Images i2 
                    WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                ) = 1
            ) img1 ON p.id = img1.idProduct
            LEFT JOIN (
                SELECT i1.idProduct, i1.imageData
                FROM Images i1
                WHERE (
                    SELECT COUNT(*) 
                    FROM Images i2 
                    WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                ) = 2
            ) img2 ON p.id = img2.idProduct
            GROUP BY
                p.id, p.productName, img1.imageData, img2.imageData;
        """;
    PreparedStatement preparedStatement = dao.conn.prepareStatement(query);
    ResultSet resultSet = preparedStatement.executeQuery();

    // Xử lý kết quả truy vấn
    System.out.println("ProductID | ProductName | MinPrice | MaxPrice | Image1 | Image2");
    while (resultSet.next()) {
        int productId = resultSet.getInt("ProductID");
        String productName = resultSet.getString("productName");
        int minPrice = resultSet.getInt("MinPrice");
        int maxPrice = resultSet.getInt("MaxPrice");
        String image1 = resultSet.getString("Image1"); // Lấy ảnh dưới dạng BLOB
        String image2 = resultSet.getString("Image2"); // Lấy ảnh dưới dạng BLOB

        // Hiển thị thông tin
        System.out.printf("%d | %s | %d | %d | %s | %s%n",
                productId,
                productName,
                minPrice,
                maxPrice,
                image1 != null ? image1 : "NULL",
                image2 != null ? image2 : "NULL"
        );
        list.addProduct(productId,productName,image1,image2,minPrice,maxPrice);
}
    return list;

    }
    public int getTotalProducts() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM products";

             PreparedStatement stmt = dao.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }

        return 0;
    }

    public ListProduct getListProductByPage(int page, int itemsPerPage) throws SQLException {
        ListProduct list = new ListProduct();
        String query = """
                SELECT
                    p.id AS ProductID,
                    p.productName,
                    MIN(pv.price) AS MinPrice,
                    MAX(pv.price) AS MaxPrice,
                    img1.imageData AS Image1,
                    img2.imageData AS Image2
                FROM
                    products p
                LEFT JOIN product_variants pv ON p.id = pv.idProduct
                LEFT JOIN (
                    SELECT i1.idProduct, i1.imageData
                    FROM Images i1
                    WHERE (
                        SELECT COUNT(*) 
                        FROM Images i2 
                        WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                    ) = 1
                ) img1 ON p.id = img1.idProduct
                LEFT JOIN (
                    SELECT i1.idProduct, i1.imageData
                    FROM Images i1
                    WHERE (
                        SELECT COUNT(*) 
                        FROM Images i2 
                        WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                    ) = 2
                ) img2 ON p.id = img2.idProduct
                GROUP BY
                    p.id, p.productName, img1.imageData, img2.imageData
                LIMIT ? OFFSET ?;
            """;
        int offset = (page - 1) * itemsPerPage;

        try (PreparedStatement stmt = dao.conn.prepareStatement(query)) {
            stmt.setInt(1, itemsPerPage);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("ProductID");
                    String productName = rs.getString("productName");
                    int minPrice = rs.getInt("MinPrice");
                    int maxPrice = rs.getInt("MaxPrice");
                    String image1 = rs.getString("Image1");
                    String image2 = rs.getString("Image2");

                    list.addProduct(productId, productName, image1, image2, minPrice, maxPrice);
                }
            }
        }
        return list;
    }
    public Products getProductDetail(String idProduct) throws SQLException {
        Products pro = new Products();
        String sql = "SELECT "
                + "p.productName, "
                + "MIN(pv.price) AS minPrice, "
                + "MAX(pv.price) AS maxPrice, "
                + "pv.productDescription, "
                + "MAX(CASE WHEN i.row_num = 1 THEN i.imageData END) AS image1, "
                + "MAX(CASE WHEN i.row_num = 2 THEN i.imageData END) AS image2, "
                + "MAX(CASE WHEN i.row_num = 3 THEN i.imageData END) AS image3, "
                + "MAX(CASE WHEN i.row_num = 4 THEN i.imageData END) AS image4 "
                + "FROM products p "
                + "JOIN product_variants pv ON p.id = pv.idProduct "
                + "LEFT JOIN ( "
                + "    SELECT i.idProduct, i.imageData, ROW_NUMBER() OVER (PARTITION BY i.idProduct ORDER BY i.id) AS row_num "
                + "    FROM Images i "
                + ") i ON p.id = i.idProduct AND i.row_num <= 4 "
                + "WHERE p.id = ? "  // Tham số điều kiện p.id
                + "GROUP BY p.id, p.productName, pv.productDescription;";
        PreparedStatement statement = dao.conn.prepareStatement(sql);
        statement.setString(1, idProduct);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {

            String productName = resultSet.getString("productName");
            double minPrice = resultSet.getDouble("minPrice");
            double maxPrice = resultSet.getDouble("maxPrice");
            String productDescription = resultSet.getString("productDescription");
            String image1 = resultSet.getString("image1");
            String image2 = resultSet.getString("image2");
            String image3 = resultSet.getString("image3");
            String image4 = resultSet.getString("image4");
            System.out.println(idProduct);
            // In kết quả ra console (hoặc có thể xử lý theo nhu cầu)
            System.out.println("Product Name: " + productName);
            System.out.println("Min Price: " + minPrice);
            System.out.println("Max Price: " + maxPrice);
            System.out.println("Description: " + productDescription);
            System.out.println("Image 1: " + image1);
            System.out.println("Image 2: " + image2);
            System.out.println("Image 3: " + image3);
            System.out.println("Image 4: " + image4);
            System.out.println("-----------------------------");
            pro.addProduct(Integer.parseInt(idProduct),productName,minPrice,maxPrice,image1,image2,image3,image4,productDescription);

        }
        return pro;
    }
    public static void main(String[] args) throws Exception {
        ServiceProduct s = new ServiceProduct();
        Transaction tr = new Transaction();
      //  System.out.println(s.getProductVariantCountByIdAndWeight(1,200));
        //System.out.println(s.getProductList(22));
       // System.out.println(s.getById("1",200));
      //  System.out.println(s.getUserIdByPhoneNumber("0912345678"));
      // System.out.println(s.insertPayment(Integer.parseInt("2"),Integer.parseInt("17"),"COD"));
s.selectTransactionHistory(3,tr);
//s.getListProduct();
//s.getProductDetail("44");
    }
}



