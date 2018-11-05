package com.aqryuz.footballTicketDemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;

@Service
public class ListeningService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ListeningService.class);
	
	@Autowired
	Web3j web3j;
	@Autowired
	ContractService service;
	
	/*@PostConstruct
    public void listen() {
        web3j.transactionObservable().subscribe(tx -> {
    		if (tx.getTo() != null && tx.getTo().equals(service.getEthAccounts())) {
    			LOGGER.info("New tx: id={}, block={}, from={}, to={}, value={}", tx.getHash(), tx.getBlockHash(), tx.getFrom(), tx.getTo(), tx.getValue().intValue());
    			service.processContracts(tx.getValue().longValue());
    		} else {
    			LOGGER.info("Not matched: id={}, to={}", tx.getHash(), tx.getTo());
    		}
    	});
    }*/
}
