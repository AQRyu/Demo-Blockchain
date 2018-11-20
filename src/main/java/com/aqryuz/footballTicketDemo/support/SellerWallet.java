package com.aqryuz.footballTicketDemo.support;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

public class SellerWallet {
	private static final Logger LOGGER = LoggerFactory.getLogger(SellerWallet.class);
	
	private static final String PASSWORD = "123456";
	private static final String PATH = "static/wallet/";
	private static final String NAME = "UTC--2018-10-15T23-36-56.809684700Z--6e3c7f832989eee14c2ae8b51b132db7b7d96a2e.json";

	public static File get() {
		try {
			File wallet = new ClassPathResource(PATH + NAME).getFile();
			LOGGER.info("Get wallet " + wallet.getName());
			return wallet;
		} catch (IOException e) {
			LOGGER.error("Wallet not found: path={}	name={}", PATH, NAME);
			return null;
		}
	}
	
	public static Credentials loadCredentials() {
		File file = get();
		try {
			Credentials credentials = WalletUtils.loadCredentials(PASSWORD, file);
			LOGGER.info("Credentials : file={}, address={}", file, credentials.getAddress());
			return credentials;
		} catch (IOException e) {
		} catch (CipherException e) {
			LOGGER.error("Wrong password file " + file.getName());
		}
		return null;
	}
}