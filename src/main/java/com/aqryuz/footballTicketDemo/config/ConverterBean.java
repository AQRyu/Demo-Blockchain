package com.aqryuz.footballTicketDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aqryuz.footballTicketDemo.support.Converter;


@Component
public class ConverterBean {
	@Bean
	public Converter converter() {
		return new Converter();
	}
}
