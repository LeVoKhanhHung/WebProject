package com.example.webapp.models;

public class Payment {
    private int id;
    private int idUser;
    private int idOrder;
    private String methodPayment;

    public Payment(int id, int idUser, int idOrder, String methodPayment) {
        this.id = id;
        this.idUser = idUser;
        this.idOrder = idOrder;
        this.methodPayment = methodPayment;
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

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(String methodPayment) {
        this.methodPayment = methodPayment;
    }
}

