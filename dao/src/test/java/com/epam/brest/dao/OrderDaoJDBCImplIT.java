package com.epam.brest.dao;

import com.epam.brest.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
@Rollback
class OrderDaoJDBCImplIT {

    private final Logger logger = LogManager.getLogger(OrderDaoJDBCImplIT.class);

    OrderDaoJDBCImpl orderDaoJDBC;

    public OrderDaoJDBCImplIT(@Autowired OrderDao orderDaoJDBC) {
        this.orderDaoJDBC = (OrderDaoJDBCImpl) orderDaoJDBC;
    }

    @Test
    void findAll() {
        logger.debug("Execute test: findAll()");
        assertNotNull(orderDaoJDBC);
        assertNotNull(orderDaoJDBC.findAll());
    }

    @Test
    void create() {
        assertNotNull(orderDaoJDBC);
        int orderSizeBefore = orderDaoJDBC.findAll().size();
        Order order = new Order("Santa Fish", new Date(2021-10-11));
        Integer newOrderId = orderDaoJDBC.create(order);
        System.out.println("newOrderId " + newOrderId);

        assertNotNull(newOrderId);
        assertEquals((int) orderSizeBefore, orderDaoJDBC.findAll().size() - 1);
    }
}