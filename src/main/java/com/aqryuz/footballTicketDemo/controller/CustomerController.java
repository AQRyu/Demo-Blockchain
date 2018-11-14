package com.aqryuz.footballTicketDemo.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.entity.OrderEntity;
import com.aqryuz.footballTicketDemo.model.OrderDetail;
import com.aqryuz.footballTicketDemo.model.Ticket;
import com.aqryuz.footballTicketDemo.service.ContractService;
import com.aqryuz.footballTicketDemo.service.EventService;
import com.aqryuz.footballTicketDemo.service.IpfsService;
import com.aqryuz.footballTicketDemo.service.OrderService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	ContractService contractService;
	@Autowired
	private EventService eventService;
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@GetMapping("/buyTicket/{id}")
	public String buyTicket(@PathVariable Long id, Model model) {
		EventEntity event = eventService.find(id);
		model.addAttribute("event",event);
		return "buyTicket";
	}

	@GetMapping("/confirm/{eventId}/{ticketId}")
	public String getConfirmPage(@PathVariable Long eventId ,@PathVariable Long ticketId, Model model) {
		EventEntity event = eventService.find(eventId);
		Ticket ticket = event.getTickets().get(ticketId.intValue());
		model.addAttribute("ticket", ticket);	
		model.addAttribute("event", event);
		return "confirm";
	}
	@Autowired
	private OrderService orderService;

	@PostMapping("/confirm")
	public String doConfirm(Long eventId, Long ticketId, Integer ticketAmount, String customerId, Double price, String privateKey, Model model) throws Exception {
		//Confirm has 2 stage:
		//1. load contract and call buyTicket to update on contract
		//2. save into OrderDB
		//Get event
		EventEntity event = eventService.find(eventId);
		
		//Customer load contract
		Credentials customer = Credentials.create(privateKey);
		String contractAddress = event.getContractHash();
		LOGGER.info("Load contract address" + contractAddress);
		contractService.load(contractAddress, customer);

		//prepare argument for transaction
		Long _ticketId = ticketId;
		Double _ticketPrice = price;
		Integer _amount = ticketAmount;
		String _customerId = customerId;

		CompletableFuture<TransactionReceipt> tr = contractService.buyTicket(_ticketId, _ticketPrice, _amount.longValue(), _customerId);
		
	//Save to db
		Long size = (long) orderService.findAll().size();
		OrderEntity order = new OrderEntity(size, customer.getAddress().toLowerCase(), eventId, ticketId, ticketAmount);
		orderService.insert(order);
		
		
		String trxHash = tr.get().getTransactionHash();
		URL url = new URL("https://rinkeby.etherscan.io/tx/" + trxHash);
		model.addAttribute("url",url);
		return "buyTicketSuccessfully";
	}

	@GetMapping("/checkTicket")
	public String getCheckTicketPage() {
		return "checkTicket";
	}
	@Autowired
	private IpfsService ipfsService;
	
	@PostMapping("/checkTicket")
	public String doCheckTicket(String addr, Model model) throws Exception {
		//Join json file manual => need to be fixed
		
		List<OrderEntity> orders = orderService.findAllBy(addr.toLowerCase());
		List<OrderDetail> customers = new ArrayList<>();
		for(OrderEntity order : orders) {
			EventEntity event = eventService.find(order.getEventId());
			Ticket ticket = event.getTickets().get(order.getTicketId().intValue());
			OrderDetail orderDetail = new OrderDetail(addr, event.getName(), event.getLeague(), ticket.getType(), order.getTicketAmount());
			customers.add(orderDetail);
			LOGGER.info("Customer history: " + orderDetail.toString());
		}
		

		model.addAttribute("customers",customers);

		return "showCustomerHistory";
	}
}
