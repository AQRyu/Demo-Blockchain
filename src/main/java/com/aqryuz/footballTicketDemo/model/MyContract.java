package com.aqryuz.footballTicketDemo.model;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;


public class MyContract extends TicketContract{

	protected MyContract(String contractAddress, Web3j web3j, Credentials credentials,
			ContractGasProvider contractGasProvider) {
		super(contractAddress, web3j, credentials, contractGasProvider);
	}

}
