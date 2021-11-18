package com.epam.brest.dao;

import com.epam.brest.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;



public class OrderDaoJDBCImpl implements OrderDao{

    private final Logger logger = LogManager.getLogger(OrderDaoJDBCImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_ALL_ORDERS = "SELECT op.order_id, op.shipper, op.date FROM order_product op ORDER BY shipper";
    private final String SQL_CREATE_ORDERS = "INSERT INTO order_product(shipper, date) VALUES(:shipper, :date)";

    @Deprecated
    public OrderDaoJDBCImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public OrderDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Order> findAll() {
        logger.debug("Start: findAll()");
        return namedParameterJdbcTemplate.query(SQL_ALL_ORDERS, new OrderRowMapper());
    }

    @Override
    public Integer create(Order order) {
        logger.debug("Start: create({})", order);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        ((MapSqlParameterSource) sqlParameterSource).addValue("shipper", order.getShipper());
        ((MapSqlParameterSource) sqlParameterSource).addValue("date", order.getDate());
        //KeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(SQL_CREATE_ORDERS, sqlParameterSource);
        // return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(Order order) {
        return null;
    }

    @Override
    public Integer delete(Integer orderId) {
        return null;
    }

    @Override
    public Integer count() {
        logger.debug("count()");
        return namedParameterJdbcTemplate
                .queryForObject("SELECT count(*) FROM order_product", new MapSqlParameterSource(), Integer.class);
    }

    private class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order = new Order();
            order.setOrderId(resultSet.getInt("order_id"));
            order.setShipper(resultSet.getString("shipper"));
            order.setDate(resultSet.getDate("date"));
            return order;
        }
    }
}
