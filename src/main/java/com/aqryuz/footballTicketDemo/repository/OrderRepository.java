package com.aqryuz.footballTicketDemo.repository;

import java.util.List;

import com.aqryuz.footballTicketDemo.entity.OrderEntity;


public interface OrderRepository {
	public void createCollection();
	
	public void insert(OrderEntity order);
	
	public void upsert(OrderEntity order);
	
	public void save(String customerAddress);
	
	public OrderEntity find(String customerAddress);
	
	public List<OrderEntity> findAll();

	List<OrderEntity> findAllBy(String customerAddress);

	public OrderEntity find(Long id);

}
