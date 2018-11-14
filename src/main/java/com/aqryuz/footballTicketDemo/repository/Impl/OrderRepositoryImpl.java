package com.aqryuz.footballTicketDemo.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aqryuz.footballTicketDemo.entity.OrderEntity;
import com.aqryuz.footballTicketDemo.repository.OrderRepository;

import io.jsondb.JsonDBTemplate;


@Repository
public class OrderRepositoryImpl implements OrderRepository{
	@Autowired
	private JsonDBTemplate jsonDBTemplate;
	
	@Override
	public void createCollection() {
		jsonDBTemplate.createCollection(OrderEntity.class);
	}

	@Override
	public void insert(OrderEntity order) {
		OrderEntity o = new OrderEntity(order);
		jsonDBTemplate.insert(o);
	}

	@Override
	public void upsert(OrderEntity order) {
		jsonDBTemplate.upsert(order);
		
	}

	@Override
	public void save(String customerAddress) {
		OrderEntity o= new OrderEntity();
		o.setCustomerAddress(customerAddress);
		jsonDBTemplate.save(o, OrderEntity.class);
	}

	@Override
	public OrderEntity find(String customerAddress) {
		return jsonDBTemplate.findById(customerAddress, OrderEntity.class);
	}

	@Override
	public List<OrderEntity> findAll() {
		return jsonDBTemplate.findAll(OrderEntity.class);
	}
	
	@Override
	public List<OrderEntity> findAllBy(String customerAddress) {
		String jxQuery = String.format("/.[customerAddress='%s']", customerAddress);
		return jsonDBTemplate.find(jxQuery, OrderEntity.class);
	}
}
