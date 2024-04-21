package com.qrscnner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QrScannerApplication {

	public static void main(String[] args) {

		SpringApplication.run(QrScannerApplication.class, args);
		System.out.println("QR Code Application Started ");
	}

}
