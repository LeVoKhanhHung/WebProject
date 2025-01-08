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

    


    public static void main(String[] args) throws SQLException {
        ProductDao s = new ProductDao();
        s.getProductDetail("1");
    }
}
