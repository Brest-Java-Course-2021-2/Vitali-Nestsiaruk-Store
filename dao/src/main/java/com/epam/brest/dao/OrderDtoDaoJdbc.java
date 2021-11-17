package com.epam.brest.dao;

import com.epam.brest.model.dto.OrderDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  Order DTO DAO implementation.
 */
@Component
public class OrderDtoDaoJdbc implements OrderDtoDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String findAllWithTotalPriceSql = "SELECT\n" +
            "\top.order_id AS orderId,\n" +
            "\top.shipper,\n" +
            "\tsum(p.amount * p.price) AS totalPrice,\n" +
            "\top.date\n" +
            "FROM\n" +
            "\torder_product op\n" +
            "LEFT JOIN product p ON\n" +
            "\top.order_id = p.order_id\n" +
            "GROUP BY\n" +
            "\top.order_id,\n" +
            "\top.shipper\n" +
            "ORDER BY\n" +
            "\top.order_id";

    public OrderDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<OrderDto> findAllWithTotalPrice() {
        List<OrderDto> orders = namedParameterJdbcTemplate.query(findAllWithTotalPriceSql,
                BeanPropertyRowMapper.newInstance(OrderDto.class));
        return orders;
    }
}
