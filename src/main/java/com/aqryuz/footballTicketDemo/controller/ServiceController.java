package com.aqryuz.footballTicketDemo.controller;

import java.math.BigInteger;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.web3j.tuples.generated.Tuple4;

import com.aqryuz.footballTicketDemo.service.ContractService;

@Controller
public class ServiceController {
	@Autowired
	private ContractService contractService;
	
	@GetMapping("/service/checkTicket")
	public String getCheckTicketPage() {
		return "checkTicket";
	}
	
	@PostMapping("/service/checkTicket")
	public String doCheckTicket(String addr, Model model) throws Exception {
		Tuple4<String, BigInteger, BigInteger, BigInteger> customer= this.contractService.checkCustomerHistory(addr).sendAsync().get();
		String customerId = customer.getValue1();
		int ticketId = customer.getValue2().intValue();
		int ticketAmount = customer.getValue3().intValue();
		Instant instant = Instant.ofEpochMilli(customer.getValue4().longValue());
		
		model.addAttribute("customerId",customerId);
		model.addAttribute("ticketId",ticketId);
		model.addAttribute("ticketAmount", ticketAmount);
		model.addAttribute("timestamp", instant.toString());
		
		return "showCustomerHistory";
	}
}
