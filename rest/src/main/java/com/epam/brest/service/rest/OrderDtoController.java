package com.epam.brest.service.rest;

import com.epam.brest.dao.OrderDaoJDBCImpl;
import com.epam.brest.model.dto.OrderDto;
import com.epam.brest.service.OrderDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class OrderDtoController {

    private static final Logger logger = LogManager.getLogger(OrderDaoJDBCImpl.class);

    private final OrderDtoService orderDtoService;

    public OrderDtoController(OrderDtoService orderDtoService) {
        this.orderDtoService = orderDtoService;
    }

    @GetMapping(value = "/orderss_dto")
    public final Collection<OrderDto> getOrderById(@PathVariable Integer id) {

        logger.debug("department()");
        return orderDtoService.findAllWithTotalPrice();
    }
}
