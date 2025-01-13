package com.example.webapp.models;

public class Supplier {
    private int id;
    private String supplierName;
    private String contactInfo;
    private String address;
    private boolean isActive;

    public Supplier(int id, String supplierName, String contactInfo, String address, boolean isActive) {
        this.id = id;
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
        this.address = address;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

