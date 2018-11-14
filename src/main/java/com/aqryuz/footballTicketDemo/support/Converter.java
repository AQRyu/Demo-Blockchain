package com.aqryuz.footballTicketDemo.support;

import org.springframework.util.SerializationUtils;

import com.aqryuz.footballTicketDemo.entity.EventEntity;


public class Converter {
	public byte[] fromEventToByteArray(EventEntity object) {
		return SerializationUtils.serialize(object);
	}

	public EventEntity fromByteArrayToEvent(byte[] bytes) {
		return (EventEntity) SerializationUtils.deserialize(bytes);
	}	
}
