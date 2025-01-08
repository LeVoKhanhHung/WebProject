package Models.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartProduct> items = new ArrayList<>();
    private double totalPrice; // Tổng tiền sau khi giảm giá
    private double rawTotalPrice; // Tổng tiền trước khi giảm giá

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getRawTotalPrice() {
        return rawTotalPrice;
    }

    public void addCart(CartProduct pro) {
        CartProduct cartProduct = new CartProduct(
                pro.id,
                pro.getName(),
                pro.price,
                pro.quantity,
                pro.getImg(),
                pro.weight,
                0, // Tổng giá sau giảm sẽ tính sau
                pro.sale
        );

        // Giá chưa giảm
        double rawProductPrice = pro.price * pro.quantity;

        // Giá sau khi giảm
        double discountedPrice = pro.price - (pro.price * pro.getSale() / 100);
        double totalProductPrice = discountedPrice * pro.quantity;

        // Gán giá trị cho sản phẩm mới
        cartProduct.rawTotal = rawProductPrice;
        cartProduct.total = totalProductPrice;

        if (!updateCart(pro)) {
            items.add(cartProduct);
            rawTotalPrice += rawProductPrice;
            totalPrice += totalProductPrice;

            System.out.println(cartProduct.rawTotal + " - Tổng tiền chưa giảm của sản phẩm vừa thêm");
            System.out.println(cartProduct.total + " - Tổng tiền đã giảm của sản phẩm vừa thêm");
        }
    }

    public boolean updateCart(CartProduct pro) {
        for (CartProduct cart : items) {
            if (pro.id.equals(cart.id) && pro.weight == Integer.parseInt(String.valueOf(cart.weight))) {
                // Cập nhật số lượng
                cart.quantity += pro.quantity;

                // Giá chưa giảm
                double rawProductPrice = pro.price * pro.quantity;

                // Giá sau khi giảm
                double discountedPrice = pro.price - (pro.price * pro.getSale() / 100);
                double totalProductPrice = discountedPrice * pro.quantity;

                // Cập nhật giá trị
                cart.rawTotal += rawProductPrice;
                cart.total += totalProductPrice;
                rawTotalPrice += rawProductPrice;
                totalPrice += totalProductPrice;

                System.out.println(cart.rawTotal + " - Tổng tiền chưa giảm của sản phẩm trong giỏ");
                System.out.println(cart.total + " - Tổng tiền đã giảm của sản phẩm trong giỏ");
                return true;
            }
        }
        return false;
    }
    public boolean removeCart(String id, String weight) {
        for (CartProduct cart : items) {
            if (cart.id.equals(String.valueOf(id)) && cart.weight == Integer.parseInt(weight)) {
                rawTotalPrice -= cart.price * cart.quantity; // Giảm giá trị chưa giảm
                totalPrice -= cart.total; // Giảm giá trị đã giảm
                return items.remove(cart);
            }
        }
        return false;
    }

    public List<CartProduct> getItems() {
        return items != null ? items : new ArrayList<>();
    }
}