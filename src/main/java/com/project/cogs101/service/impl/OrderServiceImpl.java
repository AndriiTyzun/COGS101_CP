package com.project.cogs101.service.impl;

import com.project.cogs101.domain.Order;
import com.project.cogs101.repository.OrderRepository;
import com.project.cogs101.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(findById(id));
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAllByOrderByIdAsc() {
        return orderRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }
}
