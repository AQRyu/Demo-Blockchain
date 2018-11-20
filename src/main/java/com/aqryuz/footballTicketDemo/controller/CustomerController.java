package com.aqryuz.footballTicketDemo.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.entity.OrderEntity;
import com.aqryuz.footballTicketDemo.model.OrderDetail;
import com.aqryuz.footballTicketDemo.model.Ticket;
import com.aqryuz.footballTicketDemo.service.ContractService;
import com.aqryuz.footballTicketDemo.service.EventService;
import com.aqryuz.footballTicketDemo.service.OrderService;
import com.aqryuz.footballTicketDemo.support.QRCodeGeneratorSupport;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	ContractService contractService;
	@Autowired
	private EventService eventService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private QRCodeGeneratorSupport qrcode; 
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping()
	public String getHome() {
		return "customerIndex";
	}

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
		URL etherscan = new URL("https://rinkeby.etherscan.io/tx/" + trxHash);
		model.addAttribute("etherscan",etherscan);
		return "buyTicketSuccessfully";
	}

	@GetMapping("/getHistory")
	public String getCheckCustomerHistoryPage() {
		return "getHistory";
	}
	
	@PostMapping("/getHistory")
	public String doCheckCustomerHistory(String addr, Model model) throws Exception {
		//Join json file manual => need to be fixed
		
		List<OrderEntity> orders = orderService.findAllBy(addr.toLowerCase());
		List<OrderDetail> customers = new ArrayList<>();
		for(OrderEntity order : orders) {
			EventEntity event = eventService.find(order.getEventId());
			Ticket ticket = event.getTickets().get(order.getTicketId().intValue());
			OrderDetail orderDetail = new OrderDetail(addr, event.getName(), event.getLeague(), ticket.getType(), order.getTicketAmount(), order.getId());
			customers.add(orderDetail);
		}
		
		model.addAttribute("customers",customers);

		return "showCustomerHistory";
	}
	
	
	@GetMapping(value =  "/qrcode/{orderId}", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] getQRCodePage(@PathVariable Long orderId) throws Exception {
		//The idea here is from orderId we can get the transaction that customer bought tickets from what event
		//So retrieve orderEntity will let us get customerPublicAddress and ContractHash that is the event
		OrderEntity orderEntity = orderService.find(orderId);
		String contractHash = eventService.find(orderEntity.getEventId()).getContractHash();
		contractService.load(contractHash);
		String code = contractService.checkCustomerHistory(orderEntity.getCustomerAddress()).toString();
		return qrcode.getQRCodeImage(code, 350, 350);
	}
}
