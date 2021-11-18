package com.epam.brest.service;

import com.epam.brest.model.Order;

public interface OrderService {
    Integer create(Order order);
    Integer count();
}
