package com.aqryuz.footballTicketDemo.service.Impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

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

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.model.SellerWallet;
import com.aqryuz.footballTicketDemo.model.TicketContract;
import com.aqryuz.footballTicketDemo.service.ContractService;
import com.aqryuz.footballTicketDemo.service.EventService;

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
			contract = deploy(numsTicket, hash).sendAsync().get();
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

	public RemoteCall<TicketContract> deploy(BigInteger numsTicket, String ipfsHash) {
		return TicketContract.deploy(web3j, sellerWallet, new DefaultGasProvider(), numsTicket, ipfsHash);
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
	public RemoteCall<Tuple4<String, String, BigInteger, BigInteger>> checkCustomerHistory(String addr) {
		return this.contract.checkCustomerHistory(addr);

	}

	@Override
	@Deprecated
	public Contract load(Credentials sender) {
		// TODO Auto-generated method stub
		return null;
	}
}
