package com.aqryuz.footballTicketDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsondb.JsonDBTemplate;
@Configuration
public class JsonDbTemplateConfig {
	private String dbFilesLocaltion = "src/main/resources/static/db";
	
	private String baseScanPackage = "com.aqryuz.footballTicketDemo.entity";

	@Bean
	public JsonDBTemplate jsonDBTemplate() {
		return new JsonDBTemplate(this.dbFilesLocaltion, this.baseScanPackage);
	}

	public String getDbFilesLocaltion() {
		return dbFilesLocaltion;
	}

	public void setDbFilesLocaltion(String dbFilesLocaltion) {
		this.dbFilesLocaltion = dbFilesLocaltion;
	}

	public String getBaseScanPackage() {
		return baseScanPackage;
	}

	public void setBaseScanPackage(String baseScanPackage) {
		this.baseScanPackage = baseScanPackage;
	}

	
}
