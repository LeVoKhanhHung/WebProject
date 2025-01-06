package Dao;

import Models.WishlistProduct.Product;
import Models.WishlistProduct.WishlistProduct;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDao {
    ConnDB dao = new ConnDB();

    public boolean addWishlist(int idUser, int idProduct) throws SQLException {

        String sql = "INSERT INTO wishlists (idUser, idProduct, addDate) VALUES (?, ?, NOW())";

             PreparedStatement stmt = dao.conn.prepareStatement(sql) ;

            stmt.setInt(1, idUser);
            stmt.setInt(2, idProduct);

            int rowsAffected = stmt.executeUpdate();
           if(rowsAffected > 0){
               return true;
           }
           return false;
    }
    public WishlistProduct selectWishlist(int idUser, int idProduct, double weight) throws SQLException {
        WishlistProduct wishlistProduct = new WishlistProduct();

        String sql = "SELECT \n" +
                "    w.idProduct, \n" +
                "    p.productName,\n" +
                "    w.addDate, \n" +
                "    i.imageData, \n" +
                "    pv.weight AS productSpecification\n" +
                "FROM \n" +
                "    wishlists w\n" +
                "JOIN \n" +
                "    products p ON w.idProduct = p.id\n" +
                "LEFT JOIN \n" +
                "    Images i ON p.id = i.idProduct\n" +
                "LEFT JOIN \n" +
                "    product_variants pv ON p.id = pv.idProduct\n" +
                "WHERE \n" +
                "    w.idUser = ? and pv.weight = ? and w.idProduct = ?\n" +
                "    limit 1;";
        PreparedStatement stmt = dao.conn.prepareStatement(sql) ;
        stmt.setInt(1, idUser);
        stmt.setInt(3,idProduct);
        stmt.setDouble(2,weight);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int idProduct1 = rs.getInt("idProduct");
            String name = rs.getString("productName");
            Date date = rs.getDate("addDate");
            String image = rs.getString("imageData");
            float weight1 = rs.getFloat("productSpecification");
wishlistProduct.addProductWishlist(idProduct1,name,image,weight1,date);

        }

        return wishlistProduct;
    }

    public static void main(String[] args) throws SQLException {
        WishlistDao s = new WishlistDao();


    }
}
