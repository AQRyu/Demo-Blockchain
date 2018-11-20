package com.aqryuz.footballTicketDemo.model;

public class OrderDetail {
	private String customerAddress;
	private String eventName;
	private String eventLeague;
	private String ticketType;
	private Integer numsTicketBought;
	private Long orderId;



	public OrderDetail(String customerAddress, String eventName, String eventLeague, String ticketType,
			Integer numsTicketBought, Long orderId) {
		super();
		this.customerAddress = customerAddress;
		this.eventName = eventName;
		this.eventLeague = eventLeague;
		this.ticketType = ticketType;
		this.numsTicketBought = numsTicketBought;
		this.orderId = orderId;
	}



	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventLeague() {
		return eventLeague;
	}
	public void setEventLeague(String eventLeague) {
		this.eventLeague = eventLeague;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public Integer getNumsTicketBought() {
		return numsTicketBought;
	}
	public void setNumsTicketBought(Integer numsTicketBought) {
		this.numsTicketBought = numsTicketBought;
	}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}






}
