package com.aqryuz.footballTicketDemo.support;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

//Use static variable{path, name} for demo purpose only
@Service
public class JsonDbSupport {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final String PATH = "static/db/";
	private final String NAME = "events.json";
	
	public File getDefaultFile() {
		File file = null;
		try {
			file = new ClassPathResource(PATH + NAME).getFile();
			LOGGER.info("Get file: " + NAME);
		} catch (IOException e) {
			LOGGER.error("File {} not found!",NAME);
		}
		return file;	
	}
	
	public File getFile(String name) {
		File file = null;
		try {
			file = new ClassPathResource(PATH + name).getFile();
			LOGGER.info("Get file: " + name);
		} catch (IOException e) {
			LOGGER.error("File {} not found!",name);
		}
		return file;	
	}

}
