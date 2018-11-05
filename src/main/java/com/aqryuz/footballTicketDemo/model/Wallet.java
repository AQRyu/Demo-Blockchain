package com.aqryuz.footballTicketDemo.model;

import java.io.File;

import org.springframework.core.io.ClassPathResource;

public class Wallet extends File{
	private static final long serialVersionUID = 3192522727362336422L;


	public Wallet(String pathname) {
		super(pathname);
	}
	
	private String name;
	private ClassPathResource path;
	private String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path.toString();
	}
	public void setPath(ClassPathResource path) {
		this.path = path;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
