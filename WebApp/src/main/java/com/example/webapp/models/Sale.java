package com.example.webapp.models;

import java.sql.Timestamp;

public class Sale {
    private int id;
    private int idVariant;
    private int salePercent;
    private Timestamp saleStartDate;
    private Timestamp saleEndDate;

    public Sale(int id, int idVariant, int salePercent, Timestamp saleStartDate, Timestamp saleEndDate) {
        this.id = id;
        this.idVariant = idVariant;
        this.salePercent = salePercent;
        this.saleStartDate = saleStartDate;
        this.saleEndDate = saleEndDate;
    }

    public Sale(){ }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVariant() {
        return idVariant;
    }

    public void setIdVariant(int idVariant) {
        this.idVariant = idVariant;
    }

    public int getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(int salePercent) {
        this.salePercent = salePercent;
    }

    public Timestamp getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(Timestamp saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public Timestamp getSaleEndDate() {
        return saleEndDate;
    }

    public void setSaleEndDate(Timestamp saleEndDate) {
        this.saleEndDate = saleEndDate;
    }
}

