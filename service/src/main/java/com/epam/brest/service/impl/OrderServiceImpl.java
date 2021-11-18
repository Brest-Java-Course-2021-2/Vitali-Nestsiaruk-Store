package com.epam.brest.service.impl;

import com.epam.brest.dao.OrderDao;
import com.epam.brest.model.Order;
import com.epam.brest.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    @Transactional
    public Integer create(Order order) {
        logger.debug("create({})", order);
        return this.orderDao.create(order);
    }

    @Override
    @Transactional(readOnly = true )
    public Integer count() {
        logger.debug("count()");
        return this.orderDao.count();
    }
}
