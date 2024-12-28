package Models.inforTransaction;

public class Product {
    private int id;
    private int idOrder;
    private int idProduct;
    private int quantity;
    private int price;
    private String nameProduct;

    public Product(int id, int idOrder, int idProduct, int quantity, int price, String nameProduct) {
        this.id = id;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.nameProduct = nameProduct;
    }

    public int getId() {
        return id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", idOrder=" + idOrder +
                ", idProduct=" + idProduct +
                ", quantity=" + quantity +
                ", price=" + price +
                ", nameProduct='" + nameProduct + '\'' +
                '}';
    }
}