package Models.cart;

import Models.Productt;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartProduct> items = new ArrayList<>();
    private double total = 0;
    private double totalSale = 0;

    public double getPrice() {
        return total;
    }


    public double getTotal() {
        return total;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public void addCart(Productt pro) {
        CartProduct cartProduct = new CartProduct(pro.id, pro.getName(), pro.price, pro.quantity, pro.getImg(), pro.weight, pro.getTotal(),pro.sale);
        total += pro.price * pro.quantity;
       total = total - (total* pro.getSale()/100);
if(!updateCart(pro)){
    items.add(cartProduct);
}



    }
public boolean updateCart(Productt pro){
    for (CartProduct cart:
         items) {
        if(pro.id.equals(cart.id) && pro.weight ==Integer.parseInt(String.valueOf(cart.weight))){
            cart.quantity+=pro.quantity;
            cart.total = cart.getPrice() * cart.quantity;
            System.out.println(cart.total);
            return true;
        }


    }
    return false;
}
    public boolean removeCart(String id, String weight) {
        System.out.println("id" + id + "w" + weight);
        for (CartProduct cart : items) {
            System.out.println(cart.toString());
            if (cart.id.equals(String.valueOf(id)) && cart.weight == Integer.parseInt(weight)) {
                total -= cart.price * cart.quantity;
                System.out.println("tim thay cart ne");
                return items.remove(cart);
            }
        }
        return false;
    }

    public List<CartProduct> getItems() {
        return items != null ? items : new ArrayList<>();
    }
}