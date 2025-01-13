package com.example.webapp.models;

public class OrderDetail {
    private int id;
    private int idOrder;
    private int idProduct;
    private int quantity;
    private int price;

    public OrderDetail(int id, int idOrder, int idProduct, int quantity, int price) {
        this.id = id;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

