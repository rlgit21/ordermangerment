package com.Order.BricksOrderManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Order.BricksOrderManagement.Model.CreateOrUpdateOrderRequest;
import com.Order.BricksOrderManagement.entity.Order;
import com.Order.BricksOrderManagement.service.BricksOrderService;


@RestController
@RequestMapping("/orders")
public class BricksOrderController {

	@Autowired
	private BricksOrderService bricksOrderService;

	@PostMapping
	public ResponseEntity<Long> createOrder(@RequestBody CreateOrUpdateOrderRequest request) {
		Long orderReference = bricksOrderService.createOrder(request.getBricks());
		return ResponseEntity.ok(orderReference);
	}

	@GetMapping("/{orderReference}")
	public ResponseEntity<Order> getOrder(@PathVariable Long orderReference) {
		Order order = bricksOrderService.getOrder(orderReference);
		if (order != null) {
			return ResponseEntity.ok(order);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{getAllOrdersDetails}")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = bricksOrderService.getOrders();
		return ResponseEntity.ok(orders);
	}

	@PutMapping("{updateWithOrderReference}")
	public ResponseEntity<Long> updateOrder(@PathVariable Long orderReference,
			@RequestBody CreateOrUpdateOrderRequest request) {
		Long updatedOrderReference = bricksOrderService.updateOrder(orderReference, request.getBricks());
		if (updatedOrderReference != null) {
			return ResponseEntity.ok(updatedOrderReference);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{orderReference}/fulfill")
	public ResponseEntity<Void> fulfillOrder(@PathVariable Long orderReference) {
		boolean success = bricksOrderService.fulfillOrder(orderReference);
		if (success) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
