package com.aqryuz.footballTicketDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aqryuz.footballTicketDemo.entity.OrderEntity;
import com.aqryuz.footballTicketDemo.service.OrderService;


@RestController
@RequestMapping("api")
@CrossOrigin(maxAge = 3600)
public class OrderRestController {
	@Autowired
	private OrderService orderService;

	@GetMapping("orders")
	public ResponseEntity<List<?>> findAll() {
		List<OrderEntity> eventEntity = orderService.findAll();
		return new ResponseEntity<>(eventEntity, HttpStatus.OK);
	}

	@GetMapping("orders/{id}")
	public ResponseEntity<?> find(@PathVariable Long id){
		OrderEntity eventEntity = orderService.find(id);
		return new ResponseEntity<>(eventEntity,HttpStatus.OK);
	}

	@PostMapping("orders")
	public ResponseEntity<?> add(@RequestBody OrderEntity order){
		orderService.insert(order);
		return new ResponseEntity<OrderEntity>(order, HttpStatus.OK);
	}

	@PutMapping("orders/")
	public ResponseEntity<?> upsert(@RequestBody OrderEntity order){
		orderService.upsert(order);
		return new ResponseEntity<OrderEntity>(order, HttpStatus.OK);
	}
}
