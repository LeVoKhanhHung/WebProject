package Services;

import Models.WishlistProduct.WishlistProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceWishlist {
    ServiceWishlist serviceWishlist = new ServiceWishlist();
    public boolean addWishlist(int idUser, int idProduct) throws SQLException {

        return serviceWishlist.addWishlist(idUser,idProduct);
    }
    public WishlistProduct selectWishlist(int idUser, int idProduct, double weight) throws SQLException {
      return   serviceWishlist.selectWishlist(idUser, idProduct, weight);

    }
}
