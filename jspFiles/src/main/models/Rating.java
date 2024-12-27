package models;

import java.util.Date;

public class Rating {
    private int id;
    private int idProduct;
    private int idUser;
    private int ratingRank;
    private String comment;
    private Date createDate;
    private int status;

    public Rating(int id, int idProduct, int idUser, int ratingRank, String comment, Date createDate, int status) {
        this.id = id;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.ratingRank = ratingRank;
        this.comment = comment;
        this.createDate = createDate;
        this.status = status;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getRatingRank() {
        return ratingRank;
    }

    public void setRatingRank(int ratingRank) {
        this.ratingRank = ratingRank;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

