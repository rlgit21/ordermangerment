package com.Order.BricksOrderManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Order.BricksOrderManagement.entity.Order;

public interface BricksOrderRepo  extends JpaRepository<Order, Long>{

}
