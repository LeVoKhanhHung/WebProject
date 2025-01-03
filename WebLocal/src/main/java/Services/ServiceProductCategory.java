package Services;

import Dao.ConnDB;
import Models.Product.ListProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceProductCategory {
    public ConnDB dao = new ConnDB();

    public ListProduct getProductCategory(int idCategory, int page, int itemsPerPage) throws SQLException {

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
    WHERE
        p.idCategory = ? -- Điều kiện kiểm tra theo idCategory
    GROUP BY
        p.id, p.productName, img1.imageData, img2.imageData
    LIMIT ? OFFSET ?;
""";
        int offset = (page - 1) * itemsPerPage;

        try (PreparedStatement stmt = dao.conn.prepareStatement(query)) {
            stmt.setInt(1, idCategory);
            stmt.setInt(2, itemsPerPage);
            stmt.setInt(3, offset);

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
    public int getTotalProducts(int idCategory) throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM products where idCategory = ?";

        PreparedStatement stmt = dao.conn.prepareStatement(query);
        stmt.setInt(1,idCategory);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }

        return 0;
    }

    public static void main(String[] args) throws SQLException {
        ServiceProductCategory s = new ServiceProductCategory();
        System.out.println(s.getTotalProducts(4));
    }
    }


