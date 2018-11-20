package com.aqryuz.footballTicketDemo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqryuz.footballTicketDemo.entity.OrderEntity;
import com.aqryuz.footballTicketDemo.repository.OrderRepository;
import com.aqryuz.footballTicketDemo.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void createCollection() {
		orderRepository.createCollection();
	}

	@Override
	public void insert(OrderEntity order) {
		orderRepository.insert(order);
	}

	@Override
	public void upsert(OrderEntity order) {
		orderRepository.upsert(order);
	}

	@Override
	public void save(String customerAddress) {
		orderRepository.save(customerAddress);
	}

	@Override
	public OrderEntity find(String customerAddress) {
		return orderRepository.find(customerAddress);
	}
	
	@Override
	public OrderEntity find(Long id) {
		return orderRepository.find(id);
	}

	@Override
	public List<OrderEntity> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public List<OrderEntity> findAllBy(String customerAddress) {
		return orderRepository.findAllBy(customerAddress);
	}


}
