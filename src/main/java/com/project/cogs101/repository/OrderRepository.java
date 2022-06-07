package com.project.cogs101.repository;

import com.project.cogs101.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findById(long id);
    List<Order> findAllByOrderByIdAsc();
    List<Order> findAllByUsername(String username);
}
