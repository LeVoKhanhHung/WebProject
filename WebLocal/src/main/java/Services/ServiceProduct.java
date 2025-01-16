package Services;

import Dao.ConnDB;
import Dao.ProductDao;
import Models.ManageProduct.ListProductManage;
import Models.ManageProduct.Product;
import Models.Product.ListProduct;
import Models.TopProductBuy.TopProduct;
import Models.cart.Productt;
import Models.cart.CartProduct;
import Models.cart.Cart;
import Models.inforTransaction.Transaction;
import Models.inforTransaction.TransactionHistory;
import Models.Products.Products;

import java.sql.*;
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

    public ListProduct getListProduct() throws SQLException {
    return productDao.getListProduct();

    }
    public int getTotalProducts() throws SQLException {
        return productDao.getTotalProducts();
    }

    public ListProduct getListProductByPage(int page, int itemsPerPage) throws SQLException {

        return productDao.getListProductByPage(page,itemsPerPage);
    }
    public Products getProductDetail(String idProduct) throws SQLException {

        return productDao.getProductDetail(idProduct);
    }
    public ListProduct getFilteredProducts(String filterType, int page, int itemsPerPage) throws SQLException {
        return productDao.getFilteredProducts(filterType, page, itemsPerPage);
    }
    public int getCategoryProductCounts(String categoryName) throws SQLException {
        return productDao.getCategoryProductCounts(categoryName);
    }
    public ListProductManage getAllProducts() throws SQLException {

        return productDao.getAllProducts();
    }
    public boolean updateProduct(int idProduct, double price, int quantity, String productDescription, double weight,
                                 boolean isActive) {
       return productDao.updateProduct(idProduct, price, quantity, productDescription, weight, isActive);
    }
    public void deleteProductVariant(int idProduct, float weight) throws SQLException {
       productDao.deleteProductVariant(idProduct,weight);
    }
    public ListProduct searchProductsByName(String productName) throws SQLException {
        return productDao.searchProductsByName(productName);
    }

    public ListProduct getProductsByPriceRange(double minPrice, double maxPrice) throws SQLException {
        return productDao.getProductsByPriceRange(minPrice, maxPrice);
    }

    public TopProduct getLatestProducts() {
        return productDao.getLatestProducts();
    }
    public static void main(String[] args) throws Exception {
        ServiceProduct s = new ServiceProduct();
        Transaction tr = new Transaction();
        //System.out.println(s.getProductVariantCountByIdAndWeight(1,200));
        //System.out.println(s.getProductList(22));
        //System.out.println(s.getById("1",200));
        //System.out.println(s.getUserIdByPhoneNumber("0912345678"));
        //System.out.println(s.insertPayment(Integer.parseInt("2"),Integer.parseInt("17"),"COD"));
        //s.getListProduct();
        //s.getProductDetail("44");
        System.out.println(s.getAllProducts().getItems().size());

    }
}



