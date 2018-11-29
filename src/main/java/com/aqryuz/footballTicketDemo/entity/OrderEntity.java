package com.aqryuz.footballTicketDemo.entity;

import java.sql.Date;
import java.time.LocalDate;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "orders", schemaVersion ="1.0")
public class OrderEntity {
	@Id
	private Long id;
	private String customerAddress;
	private Long eventId;
	private Long ticketId;
	private Integer ticketAmount;
	private Date timestamp;

	public OrderEntity() {
		this.timestamp = Date.valueOf(LocalDate.now());
		
	}

	public OrderEntity(Long id, String customerAddress, Long eventId, Long ticketId, Integer ticketAmount) {
		super();
		this.id = id;
		this.customerAddress = customerAddress;
		this.eventId = eventId;
		this.ticketId = ticketId;
		this.ticketAmount = ticketAmount;
		this.timestamp =Date.valueOf(LocalDate.now());
	}

	public OrderEntity(OrderEntity order) {
		super();
		this.id = order.id;
		this.customerAddress = order.customerAddress;
		this.eventId = order.eventId;
		this.ticketId = order.ticketId;
		this.ticketAmount = order.ticketAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Integer getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}




}
