package Models.Shipping;

import java.util.ArrayList;
import java.util.List;

public class Shipping {
    public List<Shippingdetail> shippingdetailList = new ArrayList<>();
    public void addShipping(String name, double price){
        shippingdetailList.add(new Shippingdetail(name, price));
    }

    public List<Shippingdetail> getItems() {
        return shippingdetailList;
    }
}
