package com.example.webapp.models;

public class Image {
    private int id;
    private int idProduct;
    private byte[] imageData;

    public Image(int id, int idProduct, byte[] imageData) {
        this.id = id;
        this.idProduct = idProduct;
        this.imageData = imageData;
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}

