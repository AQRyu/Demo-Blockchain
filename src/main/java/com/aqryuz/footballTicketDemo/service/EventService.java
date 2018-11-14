package com.aqryuz.footballTicketDemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aqryuz.footballTicketDemo.entity.EventEntity;


@Service
public interface EventService {
	public void createCollection();

	public void insert(EventEntity event);

	public void upsert(EventEntity event);

	public void save(Long id);

	public EventEntity find(Long id);

	public List<EventEntity> findAll();
}
