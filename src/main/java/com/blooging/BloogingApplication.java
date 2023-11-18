package com.blooging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloogingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloogingApplication.class, args);
		System.out.println("Started....");
	}

}
