package com.aqryuz.footballTicketDemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aqryuz.footballTicketDemo.model.Ticket;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "events", schemaVersion ="1.0")
public class EventEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3966311925560080030L;
	@Id
	private Long id;
	private String name;
	private String league;
	private String homeTeam;
	private String awayTeam;
	private Long timestamp;
	private List<Ticket> tickets = new ArrayList<>();
	private Integer numsTicket;
	private String IpfsHash;
	private String contractHash;
	
	public EventEntity() {
	}
	
	public EventEntity(EventEntity event) {
		this.id = event.getId();
		this.name = event.getName();
		this.league = event.getLeague();
		this.homeTeam = event.getHomeTeam();
		this.awayTeam = event.getAwayTeam();
		this.timestamp = event.getTimestamp();
		this.tickets = event.tickets;
		this.numsTicket = event.getNumsTicket();
		this.IpfsHash = event.IpfsHash;
		this.contractHash = event.contractHash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Integer getNumsTicket() {
		return numsTicket;
	}

	public void setNumsTicket(Integer numsTicket) {
		this.numsTicket = numsTicket;
	}

	public String getContractHash() {
		return contractHash;
	}

	public void setContractHash(String contractHash) {
		this.contractHash = contractHash;
	}

	public String getIpfsHash() {
		return IpfsHash;
	}

	public void setIpfsHash(String ipfsHash) {
		IpfsHash = ipfsHash;
	}
	
	
}
