package com.example.gccoffee.repository;

import com.example.gccoffee.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<String, Object> toORderParamMap(Order order) {
        var paramMap = new HashMap<String, Object>();

        paramMap.put("orderId", order.getOrderId());
        paramMap.put("email", order.getEmail().getAddress());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());

        return paramMap;
    }


    private Map<String, Object> toOrderItemParamMap

    @Override
    public Order insert(Order order) {
        return null;
    }
}
