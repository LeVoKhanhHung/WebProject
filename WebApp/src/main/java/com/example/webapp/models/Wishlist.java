package com.example.webapp.models;

import java.util.Date;

public class Wishlist {
    private int id;
    private int idUser;
    private int idProduct;
    private Date addDate;

    public Wishlist(int id, int idUser, int idProduct, Date addDate) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.addDate = addDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}

