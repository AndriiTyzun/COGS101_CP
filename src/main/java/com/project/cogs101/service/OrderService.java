package com.project.cogs101.service;

import com.project.cogs101.domain.Order;

import java.util.List;

public interface OrderService {
    void save(Order product);
    void delete(long id);
    Order findById(long id);
    List<Order> findAllByOrderByIdAsc();

    List<Order> findAllByUsername(String username);
    long count();
}
