package Controller;

import Services.ServiceOrder;
import Services.ServiceProduct;
import Services.ServiceTransactionHistory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Models.cart.CartProduct;
import Models.cart.Cart;
import java.io.IOException;
@WebServlet(
        value = "/CheckoutServlet"
)
public class CheckOut extends HttpServlet {
    //THuc hien chuc nang luu cac san pham khi chuan bi thanh toan;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   //    ServiceProduct orderDAO  = new ServiceProduct();
        ServiceOrder orderDAO = new ServiceOrder();
        ServiceTransactionHistory serviceTransactionHistory = new ServiceTransactionHistory();

        HttpSession session = req.getSession();
      Cart cart = (Cart) session.getAttribute("cr7");
        if (cart == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Cart is empty\"}");
            return;
        }
        String fullName = req.getParameter("fullName");
        String phoneNumber = req.getParameter("phoneNumber");
        System.out.println(phoneNumber);
        String email = req.getParameter("email");
        String city = req.getParameter("city");
        String district = req.getParameter("district");
        String address = req.getParameter("address");
        String notes = req.getParameter("notes");
        String payment = req.getParameter("paymentMethod");
        System.out.println(payment);
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ và tên không được để trống.");
        }

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống.");
        }

        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("Tỉnh/Thành phố không được để trống.");
        }

        if (district == null || district.trim().isEmpty()) {
            throw new IllegalArgumentException("Quận/Huyện không được để trống.");
        }

        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống.");
        }

// Nếu email không bắt buộc, có thể kiểm tra trống nhưng không cần xử lý lỗi
        if (email != null && email.trim().isEmpty()) {
            email = null;  // Nếu người dùng không nhập email, có thể gán giá trị null
        }

// Nếu ghi chú không bắt buộc, kiểm tra trống nhưng không cần xử lý lỗi
        if (notes != null && notes.trim().isEmpty()) {
            notes = null;  // Nếu người dùng không nhập ghi chú, có thể gán giá trị null
        }

        int idUser = (int) session.getAttribute("idUser");
//        try {
//            idUser = orderDAO.getUserIdByPhoneNumber(phoneNumber);
//            System.out.println(idUser);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        // Tạo chuỗi receiveAddress
        StringBuilder receiveAddress = new StringBuilder();
        receiveAddress.append(fullName).append(", ");
        receiveAddress.append(phoneNumber).append(", ");
        if (email != null && !email.isEmpty()) {
            receiveAddress.append(email).append(", ");
        }
        receiveAddress.append(address).append(", ");
        receiveAddress.append(district).append(", ");
        receiveAddress.append(city);

        // Thêm ghi chú (nếu có)
        if (notes != null && !notes.isEmpty()) {
            receiveAddress.append(". Notes: ").append(notes);
        }
        String receiveAddressUser= (String)receiveAddress.toString();
       // int shippingId = Integer.parseInt(req.getParameter("shippingId"));
        double totalPrice = cart.getTotalPrice();

        try {
            // Lưu thông tin đơn hàng
            int orderId = orderDAO.saveOrder(idUser, totalPrice, receiveAddressUser, 1);
            System.out.println(orderId);
            orderDAO.insertPayment(idUser,orderId,payment);
            String paymentMethod = orderDAO.paymentMethod(String.valueOf(idUser),String.valueOf(orderId));

            // Lưu chi tiết đơn hàng
            orderDAO.saveOrderDetails(orderId, cart);
            serviceTransactionHistory.saveTransactionHistory(idUser,orderId,totalPrice,paymentMethod,receiveAddressUser);

            // Xóa giỏ hàng khỏi session sau khi hoàn tất
            session.removeAttribute("cr7");

            resp.sendRedirect("hoantat.jsp");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Failed to place order\"}");
        }
    }
}
