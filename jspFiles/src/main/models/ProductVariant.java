package models;

import java.util.Date;

public class ProductVariant {
    private int id;
    private int idProduct;
    private float weight;
    private int price;
    private boolean isActive;
    private String productDescription;
    private Date importDate;

    public ProductVariant(int id, int idProduct, float weight, int price, boolean isActive, String productDescription, Date importDate) {
        this.id = id;
        this.idProduct = idProduct;
        this.weight = weight;
        this.price = price;
        this.isActive = isActive;
        this.productDescription = productDescription;
        this.importDate = importDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
}
