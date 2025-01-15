package com.example.webapp.models;

import java.sql.SQLException;

public class Product {
    private int id;
    private String productName;
    private int idCategory;
    private int idSupplier;
    private boolean isActive;

    public Product(int id, String productName, int idCategory, int idSupplier, boolean isActive) {
        this.id = id;
        this.productName = productName;
        this.idCategory = idCategory;
        this.idSupplier = idSupplier;
        this.isActive = isActive;
    }

    public Product(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
