package com.blooging;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloogingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloogingApplication.class, args);
		System.out.println("Started....");
	}

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}


}
