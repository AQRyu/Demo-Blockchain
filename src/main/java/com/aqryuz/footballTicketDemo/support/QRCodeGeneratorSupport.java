package com.aqryuz.footballTicketDemo.support;

import java.io.ByteArrayOutputStream;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeGeneratorSupport {
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public byte[] getQRCodeImage(String text, int width, int height) throws Exception  {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] pngData = pngOutputStream.toByteArray(); 
		LOGGER.info("Generate qr code successfully");
		return pngData;
	}
}
