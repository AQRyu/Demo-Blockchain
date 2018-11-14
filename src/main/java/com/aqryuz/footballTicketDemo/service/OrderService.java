package com.aqryuz.footballTicketDemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aqryuz.footballTicketDemo.entity.OrderEntity;


@Service
public interface OrderService {
	public void createCollection();

	public void insert(OrderEntity order);

	public void upsert(OrderEntity order);

	public void save(String customerAddress);

	public OrderEntity find(String customerAddress);

	public List<OrderEntity> findAll();
	
	List<OrderEntity> findAllBy(String customerAddress);
}
