package com.aqryuz.footballTicketDemo.service.Impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import com.aqryuz.footballTicketDemo.model.TicketContract;
import com.aqryuz.footballTicketDemo.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractServiceImpl.class);

	@Autowired
	Web3j web3j;
	TicketContract contract;
/*
	@PostConstruct
	public void deploy()  {
		 Credentials credentials;
		credentials = SellerWallet.loadCredentials();
		int ticketAmount = 5;
		try {
			contract = TicketContract.deploy(web3j, credentials, new DefaultGasProvider(), BigInteger.valueOf(ticketAmount)).send();

			LOGGER.info("New contract created: address={}", contract.getContractAddress());
			Optional<TransactionReceipt> tr = contract.getTransactionReceipt();
			if (tr.isPresent()) {
				LOGGER.info("Transaction receipt: from={}, to={}, gas={}", tr.get().getFrom(), tr.get().getTo(), tr.get().getGasUsed().intValue());
			}} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
*/
	@Override
	public Contract load(Credentials sender) {
		this.contract = TicketContract.load(this.contract.getContractAddress(), web3j, sender, new DefaultGasProvider());
		return this.contract;
	}

	@Override
	public CompletableFuture<TransactionReceipt> buyTicket(long id, long amount,String customerId, Double price) {
		//Convert to BigInteger
		BigInteger _id = BigInteger.valueOf(id);
		BigInteger _amount = BigInteger.valueOf(amount);
		
		BigInteger wei = Convert.toWei(BigDecimal.valueOf(price), Convert.Unit.ETHER).toBigInteger();
		BigInteger ether = wei.multiply(_amount);
		TransactionReceipt transactionReceipt = null;
		LOGGER.info("ticketID{} + amount{} + customerId{} price{}", _id, _amount, customerId, ether);
		try {
			transactionReceipt = this.contract.buyFisrtTime(_id, _amount, customerId, ether).send();
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
			// TODO Auto-generated catch block
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
	public RemoteCall<Tuple4<String,BigInteger,BigInteger,BigInteger>> checkCustomerHistory(String addr) {
		return this.contract.checkCustomerHistory(addr);
		
	}
}
