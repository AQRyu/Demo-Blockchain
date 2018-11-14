package com.aqryuz.footballTicketDemo.model;

import java.io.Serializable;

public class Ticket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6292781335290414554L;
	private Long id;
	private String type;
	private Double price;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Ticket() {};
	
	public Ticket(Long id, String type, Double price) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
	}


}
