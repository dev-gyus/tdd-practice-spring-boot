package com.example.productorderservice.order.adapter;


import com.example.productorderservice.order.domain.Order;

import java.util.HashMap;
import java.util.Map;

class InMemoryOrderRepository {
    private final Map<Long, Order> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(Order order) {
        order.assignId(++sequence);
        persistence.put(order.getId(), order);
    }
}
