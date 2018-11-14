package com.aqryuz.footballTicketDemo.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.repository.EventRepository;

import io.jsondb.JsonDBTemplate;


@Repository
public class EventRepositoryImpl implements EventRepository{
	@Autowired
	private JsonDBTemplate jsonDBTemplate;
	
	@Override
	public void createCollection() {
		jsonDBTemplate.createCollection(EventEntity.class);
	}

	@Override
	public void insert(EventEntity event) {
		EventEntity ee = new EventEntity(event);
		jsonDBTemplate.insert(ee);
	}

	@Override
	public void upsert(EventEntity event) {
		EventEntity ee = new EventEntity(event);
		jsonDBTemplate.upsert(ee);
	}

	@Override
	public void save(Long id) {
		EventEntity ee = new EventEntity();
		ee.setId(id);
		jsonDBTemplate.save(ee, EventEntity.class);
		 
	}

	@Override
	public EventEntity find(Long id) {
		return jsonDBTemplate.findById(id, EventEntity.class);
	}

	@Override
	public List<EventEntity> findAll() {
		return jsonDBTemplate.findAll(EventEntity.class);
	}

}
