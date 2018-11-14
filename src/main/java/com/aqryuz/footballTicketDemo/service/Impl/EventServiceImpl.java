package com.aqryuz.footballTicketDemo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.repository.EventRepository;
import com.aqryuz.footballTicketDemo.service.EventService;


@Service
public class EventServiceImpl implements EventService{
	@Autowired
	private EventRepository eventRepository;

	@Override
	public void createCollection() {
		eventRepository.createCollection();
	}

	@Override
	public void insert(EventEntity event) {
		eventRepository.insert(event);
	}

	@Override
	public void upsert(EventEntity event) {
		eventRepository.upsert(event);
	}

	@Override
	public void save(Long id) {
		eventRepository.save(id);
	}

	@Override
	public EventEntity find(Long id) {
		return eventRepository.find(id);
	}

	@Override
	public List<EventEntity> findAll() {
		return eventRepository.findAll();
	}

}
