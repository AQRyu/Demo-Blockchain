package com.aqryuz.footballTicketDemo.service;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;

public interface ContractService {
	public void deploy();

	public Contract load(Credentials sender);

	public CompletableFuture<EthAccounts> getEthAccounts() throws Exception;
	
	public CompletableFuture<TransactionReceipt> withdraw();
	
	public String getContractAddress();

	public CompletableFuture<TransactionReceipt> buyTicket(long id, long amount, String customerId, Double price);
	
	public RemoteCall<Tuple4<String, BigInteger, BigInteger, BigInteger>> checkCustomerHistory(String addr);
}
