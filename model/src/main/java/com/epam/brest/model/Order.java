package com.epam.brest.model;

import java.time.LocalDate;

public class Order {


    private Integer orderId;

    private String shipper;

    //private LocalDate date;

    public Order() {
    }

    public Order(String shipper) {
        this.shipper = shipper;
    }

    /*
    public Order(String shipper, LocalDate date) {
        this.shipper = shipper;
        this.date = date;
    }
    */

    public Order(Integer orderId, String shipper) {
        this.orderId = orderId;
        this.shipper = shipper;

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
    /*
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    */

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", shipper='" + shipper + '\'' +
                //", date=" + date +
                '}';
    }

}
