package Services;

import Dao.ConnDB;
import Dao.ProductDao;
import Models.Product.ListProduct;
import Models.cart.Productt;
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
    List<CartProduct> productList = new ArrayList<>();
    private ConnDB dao = new ConnDB();
    ProductDao productDao = new ProductDao();

    public List<CartProduct> getProductList(double weight) throws SQLException {

        return productDao.getProductList(weight);
    }
    public CartProduct getById(String id, int weight) throws SQLException {

        return productDao.getById(id, weight);
    }
   public int getProductVariantCountByIdAndWeight(int productId, float weight) {
        return productDao.getProductVariantCountByIdAndWeight(productId, weight);
    }


    public static void main(String[] args) throws Exception {
        ServiceProduct s = new ServiceProduct();
        Transaction tr = new Transaction();
      //  System.out.println(s.getProductVariantCountByIdAndWeight(1,200));
        //System.out.println(s.getProductList(22));
       // System.out.println(s.getById("1",200));
      //  System.out.println(s.getUserIdByPhoneNumber("0912345678"));
      // System.out.println(s.insertPayment(Integer.parseInt("2"),Integer.parseInt("17"),"COD"));

//s.getListProduct();
//s.getProductDetail("44");
    }
}



