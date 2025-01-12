package Controller.Quantity;

import Models.cart.Cart;
import Models.cart.CartProduct;
import Services.ServiceProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(
        value = "/checkQuantitySub"
)

public class CheckQuantityProductSub extends HttpServlet {
    ServiceProduct serviceProduct = new ServiceProduct();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idProduct = Integer.parseInt(req.getParameter("productID"));
            int weight = Integer.parseInt(req.getParameter("weight"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            HttpSession session = req.getSession(true);
            Cart cart = (Cart) session.getAttribute("cr7");

            if (cart == null || cart.getItems() == null) {
                System.out.println("Giỏ hàng hoặc danh sách sản phẩm bị null.");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Giỏ hàng chưa được khởi tạo.");
                return;
            }

            int checkQuantity = serviceProduct.getProductVariantCountByIdAndWeight(idProduct, weight);

            for (CartProduct pro : cart.getItems()) {
                if (pro.getId().equals(String.valueOf(idProduct)) && pro.weight == weight) {
                    pro.quantity--;

                    // Cập nhật giá trị tổng
                    double rawProductPrice = pro.price;
                    double discountedPrice = pro.price - (pro.price * pro.getSale() / 100);
                    double totalProductPrice = discountedPrice;

                    pro.rawTotal -= rawProductPrice;
                    pro.total -= totalProductPrice;
                    cart.rawTotalPrice -= rawProductPrice;
                    cart.totalPrice -= totalProductPrice;

                    // Nếu số lượng bằng 0, xóa sản phẩm khỏi giỏ
                    if (pro.quantity <= 0) {
                        cart.getItems().remove(pro);
                    }
                    break;
                }
            }

            session.setAttribute("cr7", cart);
            resp.sendRedirect("giohang.jsp");

        } catch (NumberFormatException e) {
            System.out.println("Lỗi chuyển đổi tham số: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dữ liệu không hợp lệ.");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi không mong muốn.");
        }

    }
}
