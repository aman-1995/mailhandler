package com.webstarters.mailhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MailUtilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailUtilityApplication.class, args);
	}
	
}
