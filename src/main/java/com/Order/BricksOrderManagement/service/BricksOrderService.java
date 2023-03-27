package com.Order.BricksOrderManagement.service;

import java.util.List;

import com.Order.BricksOrderManagement.entity.Order;

public interface BricksOrderService {

	Long createOrder(Integer brick);

	Order getOrder(Long reference);

	List<Order> getOrders();

	Long updateOrder(Long reference, Integer brick);

	boolean fulfillOrder(Long reference);

}
