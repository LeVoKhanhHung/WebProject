package com.example.webapp.models.feedbacks;

import java.util.Date;

public class Feedback {
    private int id;
    private int idCustomer;
    private String comment;
    private Date createDate;

    public Feedback(int id, int idCustomer, String comment, Date createDate) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.comment = comment;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

