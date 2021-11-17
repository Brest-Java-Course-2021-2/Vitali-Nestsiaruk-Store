package com.epam.brest.model.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * POJO Order for model.
 */
public class OrderDto {

    /**
     * Order Id.
     */
    private Integer orderId;

    /**
     * Shipper.
     */
    private String shipper;

    /**
     * Total price of the Order.
     */
    private BigDecimal totalPrice;

    /**
     * Data of the Order.
     */
    private Date date;

    /**
     * Constructor without arguments.
     */
    public OrderDto() {
    }

    /**
     * Constructor with shipper name.
     *
     * @param (shipper, date)
     */
    public OrderDto(String shipper, Date date) {
        this.shipper = shipper;
        this.date = date;
    }

    /**
     * Returns <code>Integer</code> representation of this orderId.
     *
     * @return orderId Order Id.
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Sets the order's identifier.
     *
     * @param orderId Order Id.
     */
    public void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * Returns <code>String</code> representation of this shipper.
     *
     * @return shipper Shipper.
     */
    public String getShipper() {
        return shipper;
    }

    /**
     * Sets the shipper's name.
     *
     * @param shipper Shipper
     */
    public void setShipper(final String shipper) {
        this.shipper = shipper;
    }

    /**
     * Returns <code>BigDecimal</code> representation of total price
     * for the Order.
     *
     * @return totalPrice Total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the order's total price.
     *
     * @param totalPrice Total price.
     */
    public void setTotalPrice(final BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Returns <code>Date</code> representation of Date
     * for the Order.
     *
     * @return date Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the order's date.
     *
     * @param date Date.
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", shipper='" + shipper + '\'' +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
