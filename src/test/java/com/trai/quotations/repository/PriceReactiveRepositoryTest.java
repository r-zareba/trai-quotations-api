package com.trai.quotations.repository;

import com.trai.quotations.price.Eurusd;
import com.trai.quotations.price.PriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
public class PriceReactiveRepositoryTest {

    List<Eurusd> pricesList = Arrays.asList(
            new Eurusd(1.1111, 1.1112, 1.1113, 1.1114, new Date()),
            new Eurusd(1.1111, 1.1112, 1.1113, 1.1114, new Date()),
            new Eurusd(1.1111, 1.1112, 1.1113, 1.1114, new Date()),
            new Eurusd(1.1111, 1.1112, 1.1113, 1.1114, new Date()),
            new Eurusd(1.1111, 1.1112, 1.1113, 1.1114, new Date()));

    @Autowired
    PriceRepository priceRepository;

    @Before
    public void setUp() {
        priceRepository.deleteAll()
                .thenMany(Flux.fromIterable(pricesList))
                .flatMap(priceRepository::save)
                .doOnNext((item -> System.out.println("Inserted")))
                .blockLast(); // Wait until all above is completed (don't use in prod code, it breaks reactive)
    }

    @Test
    public void getAllPrices() {
        StepVerifier.create(priceRepository.findAll())
                .expectSubscription()
                .expectNextCount(5)
                .verifyComplete();
    }
}
