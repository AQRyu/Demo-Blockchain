package com.aqryuz.footballTicketDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.crypto.Credentials;

import com.aqryuz.footballTicketDemo.model.SellerWallet;
import com.aqryuz.footballTicketDemo.service.ContractService;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	@Autowired
	ContractService contractService;
	
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
}
