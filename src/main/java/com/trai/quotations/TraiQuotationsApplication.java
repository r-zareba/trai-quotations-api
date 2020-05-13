package com.trai.quotations;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableScheduling
@EnableWebFlux
@SpringBootApplication
public class TraiQuotationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraiQuotationsApplication.class, args);
	}

	@Bean
	public void cappedCollection() {
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://mongodb:27017"));;
		MongoDatabase db = mongoClient.getDatabase("prices");
		db.createCollection("EURUSD", new CreateCollectionOptions()
				.capped(true)
				.sizeInBytes(4096)
				.maxDocuments(5));
	}

}
