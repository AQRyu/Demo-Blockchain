package com.aqryuz.footballTicketDemo.service;

import java.util.List;

import com.aqryuz.footballTicketDemo.entity.OrderEntity;


public interface OrderService {
	public void createCollection();

	public void insert(OrderEntity order);

	public void upsert(OrderEntity order);

	public void save(String customerAddress);

	public OrderEntity find(String customerAddress);
	
	public OrderEntity find(Long id);
	
	public List<OrderEntity> findAllBy(Long eventId);

	public List<OrderEntity> findAll();
	
	List<OrderEntity> findAllBy(String customerAddress);
}
