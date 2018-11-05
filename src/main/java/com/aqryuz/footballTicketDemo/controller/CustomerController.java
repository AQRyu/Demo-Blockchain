package com.aqryuz.footballTicketDemo.controller;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.aqryuz.footballTicketDemo.service.ContractService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	ContractService contractService;
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@GetMapping("/buyTicket")
	public String buyTicket() {
		return "buyTicket";
	}

	@GetMapping("/confirm")
	public String getConfirmPage(Model model, double price) {
		//		Double price = 0.1;
		model.addAttribute("price", price);	
		return "confirm";
	}

	@PostMapping("/confirm")
	public String doConfirm(long ticketAmount, String customerId, Double price, String privateKey, Model model) throws Exception {
		Credentials customer = Credentials.create(privateKey);
		contractService.load(customer);
		//Lỗi liên thông với smart contract: tự cập nhật ticketId
		long ticketId = (long) (price * 10) - 1;
		CompletableFuture<TransactionReceipt> tr = contractService.buyTicket(ticketId, ticketAmount, customerId, price);
		URL url = new URL("https://rinkeby.etherscan.io/tx/" + tr.get().getTransactionHash());
		model.addAttribute("url",url);
		return "buyTicketSuccessfully";
	}
}
