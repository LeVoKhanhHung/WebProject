package Services;

import Dao.ConnDB;
import Dao.OrderDao;
import Models.cart.Cart;
import Models.cart.CartProduct;
import Models.inforTransaction.Product;
import Models.inforTransaction.Transaction;
import Models.inforTransaction.TransactionHistory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrder {
    ConnDB dao = new ConnDB();
    OrderDao orderDao = new OrderDao();

    public boolean insertPayment(int idUser, int idOrder, String methodPayment) throws SQLException {
        return orderDao.insertPayment(idUser, idOrder, methodPayment);

    }
    public int saveOrder(int userId, Double totalPrice, String receiveAddress, int shippingId) throws SQLException {

        return orderDao.saveOrder(userId, totalPrice, receiveAddress, shippingId);
    }
    public List<Product> getOrderDetailsByIdOrder(int idOrder) throws SQLException {

        return orderDao.getOrderDetailsByIdOrder(idOrder);
    }
    public void saveOrderDetails(int orderId, Cart cart) throws SQLException {
       orderDao.saveOrderDetails(orderId, cart);
    }
    public String getReceiveDate(int idUser, int idOrder) throws SQLException {

        return orderDao.getReceiveDate(idUser, idOrder);
    }
    public String paymentMethod(String idUser, String idOrder) throws SQLException {


        return orderDao.paymentMethod(idUser,idOrder);

    }
}
