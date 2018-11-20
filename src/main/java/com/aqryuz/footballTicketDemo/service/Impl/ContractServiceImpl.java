package com.aqryuz.footballTicketDemo.service.Impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.model.TicketContract;
import com.aqryuz.footballTicketDemo.service.ContractService;
import com.aqryuz.footballTicketDemo.service.EventService;
import com.aqryuz.footballTicketDemo.support.SellerWallet;

@Service
public class ContractServiceImpl implements ContractService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractServiceImpl.class);

	@Autowired
	Web3j web3j;
	TicketContract contract;
	Credentials sellerWallet =  SellerWallet.loadCredentials();
	@Autowired
	EventService eventService;

//	@PostConstruct
	public void deploy()  {

		BigInteger numsTicket = BigInteger.valueOf(1000L);
		String hash = "QmX933nNLrJzysb6iH5o1NpRfUYnGfzbgg4RYeQ62MBy9m";

		try {
			contract = deploy(numsTicket, hash);
			String contractAddress = contract.getContractAddress();
			EventEntity event = eventService.find(1L);
			event.setContractHash(contractAddress);
			eventService.upsert(event);

			LOGGER.info("New contract created: address={}", contract.getContractAddress());
			Optional<TransactionReceipt> tr = contract.getTransactionReceipt();
			if (tr.isPresent()) {
				LOGGER.info("Transaction receipt: from={}, to={}, gas={}", tr.get().getFrom(), tr.get().getTo(), tr.get().getGasUsed().intValue());
			}} catch (Exception e) {
				e.printStackTrace();
			}
	}
	@Override
	public Contract load(String contractAddress) {
		return load(contractAddress, SellerWallet.loadCredentials());
	}
	
	public TicketContract deploy(BigInteger numsTicket, String ipfsHash) {
		try {
			contract =  TicketContract.deploy(web3j, sellerWallet, new DefaultGasProvider(), numsTicket, ipfsHash).sendAsync().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return contract;
	}

	@Override
	public Contract load(String contractAddress, Credentials sender) {
		try {
			this.contract = TicketContract.load(contractAddress, web3j, sender, new DefaultGasProvider());
			LOGGER.info("Load contract: {}", contract.getContractAddress());
		} catch (Exception e) {
			LOGGER.error("Load contract fail");
		}
		return contract;
	}

	@Override
	public CompletableFuture<TransactionReceipt> buyTicket(Long _ticketId, Double _ticketPrice, Long _amount, String _customerId) {
		//Convert to BigInteger
		BigInteger amount = BigInteger.valueOf(_amount);
		BigInteger ticketId = BigInteger.valueOf(_ticketId);

		BigInteger wei = Convert.toWei(BigDecimal.valueOf(_ticketPrice), Convert.Unit.ETHER).toBigInteger();
		BigInteger ether = wei.multiply(amount);
		TransactionReceipt transactionReceipt = null;
		LOGGER.info("ticketID{} + amount{} + customerId{} price{}", _ticketId, _amount, _customerId, ether);
		try {
			transactionReceipt = this.contract.buyFisrtTime(ticketId, amount, _customerId, ether).send();
			LOGGER.info("Buy ticket successfully, check at " + transactionReceipt.getTransactionHash());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("Error on transaction " + transactionReceipt.getTransactionHash());
		}
		return CompletableFuture.completedFuture(transactionReceipt);
	}

	@Override
	public CompletableFuture<TransactionReceipt> withdraw() {
		TransactionReceipt transactionReceipt = null;
		try {
			transactionReceipt = this.contract.withdraw().send();
			LOGGER.info("Withdraw successfully, check at " + transactionReceipt.getTransactionHash());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(transactionReceipt);
	}

	public CompletableFuture<EthAccounts> getEthAccounts() throws Exception {
		EthAccounts result = new EthAccounts();
		result = this.web3j.ethAccounts()
				.sendAsync() 
				.get();
		return CompletableFuture.completedFuture(result);
	}

	@Override
	public String getContractAddress() {
		return this.contract.getContractAddress();
	}
	
	@Override
	public List<Object> checkCustomerHistory(String addr) {
		Tuple4<String,String,BigInteger,BigInteger> tuple4 = getCustomerHistory(addr);
		return Arrays.asList(tuple4.getValue1(), tuple4.getValue2(), tuple4.getValue3(), tuple4.getValue4());
	}

	public Tuple4<String, String, BigInteger, BigInteger> getCustomerHistory(String addr) {
		try {
			return this.contract.checkCustomerHistory(addr).sendAsync().get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error("Call checkCustomerHistory function failed");
			return null;
		}
	}

	@Override
	@Deprecated
	public Contract load(Credentials sender) {
		// TODO Auto-generated method stub
		return null;
	}
}
