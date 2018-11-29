package com.aqryuz.footballTicketDemo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ConfirmPage {
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 9, max = 12, message = "Số cmnd phải 9 ký tự hoặc thẻ căn cước phải 12 ký tự")
	private String customerId;
	private Integer ticketAmount;
	private Double price;
	
	@JsonIgnore
	private String privateKey;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(Integer numsTicket) {
		this.ticketAmount = numsTicket;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	
}
