package Dao;

import Models.Product.ListProduct;
import Models.Products.Products;
import Models.cart.CartProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    ConnDB dao = new ConnDB();

    public ProductDao() {
        this.dao = ConnDB.getInstance(); // Lấy instance duy nhất của ConnDB
    }

public List<CartProduct> getProductList(double weight) throws SQLException {
        List<CartProduct> productList = new ArrayList<>(); // Khởi tạo danh sách sản phẩm

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
            CartProduct product   =   new CartProduct(productID,productName,productPrice,productQuantity,productImage,productWeight,totalPrice,salePercent);

            productList.add(product);
        }




        return productList;
    }
    
public CartProduct getById(String id, int weight) throws SQLException {
        List<CartProduct> productList = getProductList(weight);
        for (CartProduct product : productList) {
            if (product.getId().equals(id) && product.weight == weight) {
                return product;
            }
        }
        System.out.println("Sản phẩm không tồn tại");
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

    public int getTotalProducts() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM products";

        try (PreparedStatement stmt = dao.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }


    public static void main(String[] args) throws SQLException {
        ProductDao s = new ProductDao();
        s.getProductDetail("1");
    }
}
