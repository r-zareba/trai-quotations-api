package com.trai.quotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableScheduling
@EnableWebFlux
@SpringBootApplication
public class TraiQuotationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraiQuotationsApplication.class, args);
	}

}
