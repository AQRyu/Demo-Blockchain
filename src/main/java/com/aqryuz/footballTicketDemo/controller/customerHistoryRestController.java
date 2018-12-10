package com.aqryuz.footballTicketDemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.entity.OrderEntity;
import com.aqryuz.footballTicketDemo.service.ContractService;
import com.aqryuz.footballTicketDemo.service.EventService;
import com.aqryuz.footballTicketDemo.service.OrderService;

@RestController
@RequestMapping("api")
public class customerHistoryRestController {
	@Autowired
	private EventService eventService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ContractService contractService;
	@GetMapping("tokens/{eventId}")
	public ResponseEntity<?> findAllToken(@PathVariable Long eventId){
		EventEntity event = eventService.find(eventId);
		contractService.load(event.getContractHash());
		List<OrderEntity> orders = orderService.findAllBy(eventId);
		orders.stream().forEach(System.out::println);
		List<String> tokens = new ArrayList<>();
		orders.stream().forEach(
				order -> tokens.add(contractService.getCustomerDetail(order.getCustomerAddress()).toString()));
		System.out.println(tokens);
		return new ResponseEntity<>(tokens,HttpStatus.OK);
	}

}
