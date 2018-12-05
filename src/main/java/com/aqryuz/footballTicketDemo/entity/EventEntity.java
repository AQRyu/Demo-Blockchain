package com.aqryuz.footballTicketDemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull(message = "Hãy nhập mã vé")
	private Long id;
	@NotBlank(message = "Hãy nhập tên trận đấu")
	private String name;
	@NotBlank(message = "Hãy nhập tên giải đấu")
	private String league;
	@NotBlank(message = "Hãy nhập tên đội nhà")
	private String homeTeam;
	@NotBlank(message = "Hãy nhập tên đội khách")
	private String awayTeam;
	@NotBlank(message = "Hãy nhập tên sân vận động")
	private String stadium;
	@NotBlank(message = "Hãy nhập ngày diễn ra trận đấu")
	private String date;
	@NotBlank(message = "Hãy nhập giờ diễn ra trận đấu")
	private String time;
	private List<Ticket> tickets = new ArrayList<>();
	@NotNull(message = "Hãy nhập tổng số lượng vé bán ra")
	private Integer numsTicket;
	private String IpfsHash;
	private String contractHash;
	
	public EventEntity() {
		super();
		this.tickets = Arrays.asList(
				new Ticket(0L, "A", 0.1),
				new Ticket(1L, "B", 0.2),
				new Ticket(2L, "C", 0.3),
				new Ticket(3L, "D", 0.4)
				);
	}
	
	public EventEntity(Long id, String name, String league, String homeTeam, String awayTeam, String stadium,
			String date, String time, List<Ticket> tickets, Integer numsTicket, String ipfsHash,
			String contractHash) {
		super();
		this.id = id;
		this.name = name;
		this.league = league;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.stadium = stadium;
		this.date = date;
		this.time = time;
		this.tickets = tickets;
		this.numsTicket = numsTicket;
		IpfsHash = ipfsHash;
		this.contractHash = contractHash;
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

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
