package com.epam.brest.model;

import java.util.Date;

public class Order {

    private Integer orderId;

    private String shipper;

    private Date date;

    public Order() {
    }

    public Order(String shipper, Date date) {
        this.shipper = shipper;
        this.date = date;
    }

    public Order(Integer orderId, String shipper, Date date) {
        this.orderId = orderId;
        this.shipper = shipper;
        this.date = date;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", shipper='" + shipper + '\'' +
                ", date=" + date +
                '}';
    }
}
