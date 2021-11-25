package com.epam.brest.service;

import com.epam.brest.model.Order;

public interface OrderService {
    Order getOrderById(Integer orderId);
    Integer create(Order order);
    Integer update(Order order);
    Integer delete(Integer departmentId);
    Integer count();
}
