//package com.trai.quotations.price;
//
//import com.mongodb.MongoClient;
//import com.mongodb.client.MongoDatabase;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class MongoConfig {
//
//    private static final String host = "localhost";
//    private static final int port = 27017;
//    private static final String dbName = "prices";
//
//    @Bean
//    public MongoDatabase mongoDatabase() {
//        MongoClient mongoClient = new MongoClient(host, port);
//        return mongoClient.getDatabase(dbName);
//    }
//
//}