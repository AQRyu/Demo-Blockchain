package com.aqryuz.footballTicketDemo.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqryuz.footballTicketDemo.entity.EventEntity;
import com.aqryuz.footballTicketDemo.support.Converter;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
@Service
public class IpfsService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	IPFS ipfs = new IPFS("ipfs.infura.io",5001,"/api/v0/",true);
	@Autowired
	private Converter converter;
	@Autowired
	private EventService eventService;
//	@PostConstruct
	public void test() throws IOException, ClassNotFoundException  {
		//1. try to create an event and add to IPFS node
		//Create event
		EventEntity event = eventService.find(1L);
		//add to IPFS node
		
		//convert event to byte[] and add
		String hash = add(event);
		//print out the event's hash
		LOGGER.info("Hash: " + hash.toString());
		event.setIpfsHash(hash);
		eventService.upsert(event);
		//2. try to get event from IPFS node
		event = cat(hash);
		LOGGER.info("get event from IPFS: " + event.toString());
	}
	
	public String add(EventEntity event) {
		NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(converter.fromEventToByteArray(event));
		try {
			MerkleNode result = ipfs.add(file).get(0);
			LOGGER.info("add file successfully");
			return result.hash.toString();
		} catch (IOException e) {
			LOGGER.error("Add file error, check again");
		}
		return null;
	}
	
	public EventEntity cat(String hash) {
		EventEntity event = new EventEntity();
		try {
			LOGGER.info(hash);
			Multihash h = Multihash.fromBase58(hash);
			byte[] object = ipfs.cat(h);
			LOGGER.info("convert byte to event...");
			event = converter.fromByteArrayToEvent(object);
			LOGGER.info("convert byte to event successfully");
		} catch (IOException e) {
			LOGGER.error("Get error when convert byte to event");
		}
		return event;
	}	
}
