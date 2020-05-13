package com.trai.quotations.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;


@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceRepository priceRepository;

    @GetMapping(path = "/test", produces = "application/json")
    public Flux<Integer> getTestFlux() {
        return Flux.just(1, 2, 3, 4, 5);

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/EURUSD", params = {"size"}, produces = "application/json")
    public Flux<Eurusd> getPrices(final @RequestParam("size") int size, ServerHttpResponse response) {
        return priceRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"))
                .take(size);

    }

    @PostMapping(path = "/{asset}", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPrice(@PathVariable String asset, @RequestBody Price price) {

//        MongoCollection<Document> collection = mongoDatabase.getCollection(asset);
//
//        Document doc = new Document("timestamp", new Date())
//                .append("open", 1.1111)
//                .append("high", 1.1112)
//                .append("low", 1.1109)
//                .append("close", 1.1110);
//
//        collection.insertOne(doc);
    }


    @GetMapping("/")
    public Mono<String> greet(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Hello, %s", name));
    }

}
