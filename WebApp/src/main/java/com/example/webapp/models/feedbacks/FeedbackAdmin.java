package com.example.webapp.models.feedbacks;

import java.util.Date;

public class FeedbackAdmin {
    private int id;
    private int idCustomer;
    private String comment;
    private Date createDate;
    private String customerName;
    private String customerEmail;
    private String customerPhone;

    public FeedbackAdmin() {
    }

    public FeedbackAdmin(int id, int idCustomer, String comment, Date createDate, String customerName, String customerEmail, String customerPhone) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.comment = comment;
        this.createDate = createDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Override
    public String toString() {
        return "FeedbackAdmin{" +
                "id=" + id +
                ", idCustomer=" + idCustomer +
                ", comment='" + comment + '\'' +
                ", createDate=" + createDate +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                '}';
    }
}

