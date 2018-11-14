package com.aqryuz.footballTicketDemo.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.model.Ticket;
import com.aqryuz.footballTicketDemo.repository.EventRepository;


@RestController
@RequestMapping("/api")
public class EventController {
	@Autowired
	private EventRepository eventRepository;

//@PostConstruct
	public void createCollection() {
		List<Ticket> tickets = new ArrayList<Ticket>();
		Ticket ticket0 = new Ticket(0L,"A", 0.1);
		Ticket ticket1 = new Ticket(1L,"B", 0.2);
		Ticket ticket2 = new Ticket(2L,"C", 0.3);
		Ticket ticket3 = new Ticket(3L,"D", 0.4);
		tickets.add(ticket0);
		tickets.add(ticket1);
		tickets.add(ticket2);
		tickets.add(ticket3);
		

		EventEntity entity = new EventEntity();
		entity.setId(1232L);
		entity.setName("testing");
		entity.setHomeTeam("Real Madrid");
		entity.setAwayTeam("Barcelona");
		entity.setLeague("La liga");
		entity.setIpfsHash("ipfsHash");
		entity.setContractHash("ContractHash");
		entity.setNumsTicket(500);
		entity.setTickets(tickets);
		entity.setTimestamp(Instant.now().getEpochSecond());
		eventRepository.upsert(entity);
	}

	@GetMapping("/events")
	public ResponseEntity<List<?>> findAll() {
		List<EventEntity> eventEntity = eventRepository.findAll();
		return new ResponseEntity<>(eventEntity, HttpStatus.OK);
	}

	@GetMapping("/events/{id}")
	public ResponseEntity<?> find(@PathVariable Long id){
		EventEntity eventEntity = eventRepository.find(id);
		return new ResponseEntity<>(eventEntity,HttpStatus.OK);
	}

	@PostMapping("/events")
	public ResponseEntity<?> add(@RequestBody EventEntity event){
		EventEntity eventEntity = new EventEntity(event);
		eventRepository.insert(eventEntity);
		return new ResponseEntity<EventEntity>(eventEntity, HttpStatus.OK);
	}

	@PutMapping("/events/")
	public ResponseEntity<?> upsert(@RequestBody EventEntity event){
		EventEntity eventEntity = new EventEntity(event);
		eventRepository.upsert(eventEntity);
		return new ResponseEntity<EventEntity>(eventEntity, HttpStatus.OK);
	}
}
