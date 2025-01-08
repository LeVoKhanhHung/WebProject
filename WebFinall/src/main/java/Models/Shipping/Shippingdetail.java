package Models.Shipping;

public class Shippingdetail {
    public String name;
    public double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Shippingdetail(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Shippingdetail{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
