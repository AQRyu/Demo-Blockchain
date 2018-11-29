package com.aqryuz.footballTicketDemo.service;

import java.math.BigInteger;
import java.util.List;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;

import com.aqryuz.footballTicketDemo.model.TicketContract;

public interface ContractService {
	public void deploy();
	
	public TicketContract deploy(BigInteger numsTicket, String ipfsHash);

	public Contract load(Credentials sender);
	
	public Contract load(String contractAddress);

	public Contract load(String contractAddress, Credentials sender);

	public EthAccounts getEthAccounts() throws Exception;

	public TransactionReceipt killEvent();

	public String getContractAddress();

	public List<Object> getCustomerDetail(String addr);

	public TransactionReceipt buyTicket(Long _ticketId, Double _ticketPrice, Long _amount,
			String _customerId);
}
