package com.Order.BricksOrderManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Order.BricksOrderManagement.entity.Order;
import com.Order.BricksOrderManagement.repo.BricksOrderRepo;

@Service
public class BricksOrderServiceImp implements BricksOrderService {

	
	@Autowired
	private BricksOrderRepo bricksOrderRepo;
	
	@Override
	public Long createOrder(Integer brick) {
		Long orderReference = generateOrderReference();
		Order order = new Order(orderReference, brick, false);
		bricksOrderRepo.save(order);
		return orderReference;
	}

	@Override
	public Order getOrder(Long reference) {
		Optional<Order> findById = bricksOrderRepo.findById(reference);
		if (findById.isPresent()) {
			return findById.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Order> getOrders() {
		List<Order> findAll = bricksOrderRepo.findAll();
		return findAll;
	}

	@Override
	public Long updateOrder(Long reference, Integer brick) {
		Optional<Order> findById = bricksOrderRepo.findById(reference);
		if (findById.isPresent()) {
			Order order = findById.get();
			if (!order.isDispatched()) {
				order.setBricks(brick);
				bricksOrderRepo.save(order);
				return order.getOrderRef(); 
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean fulfillOrder(Long reference) {
		Optional<Order> findById = bricksOrderRepo.findById(reference);
		if (findById.isPresent()) {
			Order order = findById.get();
			order.setDispatched(true);
			bricksOrderRepo.save(order);
			return order.isDispatched();
		} else {
			return false;
		}
		
	}

	Long generateOrderReference() {
        return System.currentTimeMillis();
    }
	
}
