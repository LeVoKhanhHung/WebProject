package models;

import java.util.Date;

public class TransactionHistory {
    private int idHistory;
    private Date transactionDate;
    private float totalPrice;
    private int idOrder;
    private int idUser;
    private String paymentMethod;
    private String shippingAddress;

    public TransactionHistory(int idHistory, Date transactionDate, float totalPrice, int idOrder, int idUser, String paymentMethod, String shippingAddress) {
        this.idHistory = idHistory;
        this.transactionDate = transactionDate;
        this.totalPrice = totalPrice;
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}

