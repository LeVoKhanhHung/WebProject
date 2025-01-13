package com.example.webapp.models;

public class Shipping {
    private int id;
    private String name;
    private float shippingFee;

    public Shipping(int id, String name, float shippingFee) {
        this.id = id;
        this.name = name;
        this.shippingFee = shippingFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(float shippingFee) {
        this.shippingFee = shippingFee;
    }
}

