package com.example.webapp.models;

import java.util.Date;

public class Sale {
    private int id;
    private int idVariant;
    private int salePercent;
    private Date saleStartDate;
    private Date saleEndDate;

    public Sale(int id, int idVariant, int salePercent, Date saleStartDate, Date saleEndDate) {
        this.id = id;
        this.idVariant = idVariant;
        this.salePercent = salePercent;
        this.saleStartDate = saleStartDate;
        this.saleEndDate = saleEndDate;
    }

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

    public Date getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public Date getSaleEndDate() {
        return saleEndDate;
    }

    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
    }
}

