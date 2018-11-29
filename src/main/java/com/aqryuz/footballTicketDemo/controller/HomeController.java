package com.aqryuz.footballTicketDemo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.service.EventService;

@Controller
public class HomeController {
	@Autowired
	private EventService eventService;
	@GetMapping("/")
	public String index(Model model) {
		List<EventEntity> events = eventService.findAll();
		Collections.reverse(events);
		events = events.stream().filter(e -> e.getDate().after(Date.valueOf(LocalDate.now())))
		.collect(Collectors.toList());
		model.addAttribute("events",events);
		return "index";
	}
	
}
