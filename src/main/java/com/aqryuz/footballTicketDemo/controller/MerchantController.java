package com.aqryuz.footballTicketDemo.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.crypto.Credentials;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.model.Ticket;
import com.aqryuz.footballTicketDemo.service.ContractService;
import com.aqryuz.footballTicketDemo.service.EventService;
import com.aqryuz.footballTicketDemo.service.IpfsService;
import com.aqryuz.footballTicketDemo.support.SellerWallet;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	@Autowired
	ContractService contractService;
	@Autowired
	EventService eventService;
	@Autowired
	IpfsService ipfsService;
	
	@GetMapping()
	public String home() {
		return "merchantIndex";
	}
	
	@GetMapping("/withdraw")
	public String getWithdrawPage() {
		return "withdraw";
	}

	@PostMapping("/withdraw")
	public String doWithdraw() {
		Credentials seller = SellerWallet.loadCredentials();
		contractService.load(seller);
		contractService.withdraw();
		return "redirect:/";
	}
	
	@GetMapping("/createTicket")
	public String getCreateTicketPage(Model model) {
		model.addAttribute("event", new EventEntity());
		return "createTicket";
	}
	
	@PostMapping("/createTicket")
	public String doCreateTicket(Model model, EventEntity event, Double ticketA, Double ticketB, Double ticketC, Double ticketD) {
	//Prepare event
		List<Ticket> tickets = event.getTickets();
		tickets.get(0).setPrice(ticketA);
		tickets.get(1).setPrice(ticketB);
		tickets.get(2).setPrice(ticketC);
		tickets.get(3).setPrice(ticketD);
		event.setTickets(tickets);
		
	//Do update event:
		//1. add event upto ipfs cloud
		String ipfsHash = ipfsService.add(event);
		//2. deploy contract with ipfsHash
		String contractHash = contractService.deploy(BigInteger.valueOf(event.getNumsTicket()), ipfsHash).getContractAddress();
		//3. update event object and insert event to db
		event.setIpfsHash(ipfsHash);
		event.setContractHash(contractHash);
		eventService.upsert(event);
		
		model.addAttribute("event", event);
		return "redirect:/";
	}
}
