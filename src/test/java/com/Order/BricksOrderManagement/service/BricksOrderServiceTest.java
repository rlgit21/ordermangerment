package com.Order.BricksOrderManagement.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Order.BricksOrderManagement.entity.Order;
import com.Order.BricksOrderManagement.repo.BricksOrderRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BricksOrderServiceTest {
	
	@Mock
	BricksOrderRepo bricksOrderRepo;
	
	@Spy
	@InjectMocks
	BricksOrderServiceImp brickOrderService;
	
	@Mock
	Order order;
	
	
	@Test 
	public void createSuccessOrder() {
		
		when(bricksOrderRepo.save(Mockito.any(Order.class)))
        .thenAnswer(i -> i.getArguments()[0]);
		long ref = brickOrderService.generateOrderReference();
		when(brickOrderService.generateOrderReference()).thenReturn(ref);
		long actualREf = brickOrderService.createOrder(10);
		verify(bricksOrderRepo,times(1)).save(Mockito.any(Order.class));

		assertEquals(ref, actualREf);
	}
	
	@Test
	public void getSuccessOrder() { 
		long ref = brickOrderService.generateOrderReference();
		Optional<Order> sampleOrder = Optional.of(new Order(ref,10,true));
		when(bricksOrderRepo.findById(Mockito.any(Long.class)))
        .thenReturn(sampleOrder);
		Order actualOrder = brickOrderService.getOrder(ref);
		verify(bricksOrderRepo,times(1)).findById(Mockito.any(Long.class));

		assertEquals(sampleOrder.get().getBricks(), actualOrder.getBricks());
		assertEquals(sampleOrder.get().getOrderRef(), actualOrder.getOrderRef());
		assertEquals(sampleOrder.get().isDispatched(), actualOrder.isDispatched());
	}

	@Test
	public void getAllOrders() { 
		List<Order> oldExistingOrders = new ArrayList<Order>();
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),10,true));
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),20,false));
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),30,true));
		
		when(bricksOrderRepo.findAll()).thenReturn(oldExistingOrders) ;
		List<Order> allOrders = brickOrderService.getOrders();
		verify(bricksOrderRepo,times(1)).findAll();
		
		assertEquals(oldExistingOrders, allOrders);
	}
	
	@Test
	public void updateOrderifNotdispacthed() {

		List<Order> oldExistingOrders = new ArrayList<Order>();
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),10,true));
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),20,false));
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),30,true));
		
		Optional<Order> sampleOrder = Optional.of((oldExistingOrders.get(1)));
		when(bricksOrderRepo.findById(Mockito.any(Long.class)))
        .thenReturn(sampleOrder);
		
		Long newUpdatedRef = brickOrderService.updateOrder(oldExistingOrders.get(1).getOrderRef(),50);
		verify(bricksOrderRepo,times(1)).findById(Mockito.any(Long.class));
		verify(bricksOrderRepo,times(1)).save(Mockito.any(Order.class));
		
		
		assertEquals(oldExistingOrders.get(1).getOrderRef(), newUpdatedRef);
		assertEquals(oldExistingOrders.get(1).getBricks(), 50);
	}
	
	@Test
	public void dontUpdateOrderifItsDispatched() {
		List<Order> oldExistingOrders = new ArrayList<Order>();
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),10,true));
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),20,false));
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),30,true));
		
		Optional<Order> sampleOrder = Optional.of((oldExistingOrders.get(0)));
		when(bricksOrderRepo.findById(Mockito.any(Long.class)))
        .thenReturn(sampleOrder);
		
		Long newUpdatedRef = brickOrderService.updateOrder(oldExistingOrders.get(0).getOrderRef(),50);
		verify(bricksOrderRepo,times(1)).findById(Mockito.any(Long.class));
		verify(bricksOrderRepo,times(0)).save(Mockito.any(Order.class));
		
		
		assertEquals(null, newUpdatedRef);
		assertEquals(oldExistingOrders.get(0).getBricks(), 10);
	}
	
	@Test
	public void fulfillOrder() {
		
		List<Order> oldExistingOrders = new ArrayList<Order>();
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),10,true));
		
		Optional<Order> sampleOrder = Optional.of(oldExistingOrders.get(0));
		when(bricksOrderRepo.findById(sampleOrder.get().getOrderRef()))
        .thenReturn(sampleOrder);
		
		boolean isRefPresent = brickOrderService.fulfillOrder(sampleOrder.get().getOrderRef());
		verify(bricksOrderRepo,times(1)).findById(Mockito.any(Long.class));
		verify(bricksOrderRepo,times(1)).save(Mockito.any(Order.class));
		
		assertEquals(true, isRefPresent);
	}
	
	@Test
	public void fulfillOrderRefNotPresent() {
		
		List<Order> oldExistingOrders = new ArrayList<Order>();
		oldExistingOrders.add(new Order(brickOrderService.generateOrderReference(),10,true));
		
		Optional<Order> sampleOrder = Optional.of(oldExistingOrders.get(0));
		when(bricksOrderRepo.findById(sampleOrder.get().getOrderRef()))
        .thenReturn(sampleOrder);
		
		boolean isRefPresent = brickOrderService.fulfillOrder(brickOrderService.generateOrderReference());
		verify(bricksOrderRepo,times(1)).findById(Mockito.any(Long.class));

		
		assertEquals(false, isRefPresent);
	
	}
}
