package com.aqryuz.footballTicketDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcesHandler implements WebMvcConfigurer{
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/wallet/**").addResourceLocations("file:wallet/");
			registry.addResourceHandler("/db/**").addResourceLocations("file:db/");
		}
}