package Models.cart;

import java.io.Serializable;

public class CartProduct implements Serializable {
    private int productId;
    public String id;
    public String name;
    public double price;
    public int quantity;
    public String img;
    public int weight;
    public double total;
    public double sale;
    public double rawTotal;

    public double getRawTotal() {
        return rawTotal;
    }

    public int getProductId() {
        return productId;
    }

    public double getSale() {
        return sale;
    }

    // Constructor
    public CartProduct(String id, String name, double price, int quantity, String img, int weight, double total,double sale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.weight = weight;
        this.total = total;
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", img='" + img + '\'' +
                ", weight=" + weight +
                ", total=" + total +
                '}';
    }

    // Getters (phải tuân theo JavaBeans)
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImg() {
        return img;
    }

    public int getWeight() {
        return weight;
    }

    public double getTotal() {
        return total;
    }
}