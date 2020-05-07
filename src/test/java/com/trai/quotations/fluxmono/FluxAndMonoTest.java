package com.trai.quotations.fluxmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Spring Reactive")
                .concatWith(Flux.error(new RuntimeException("Error occurred")))
                .log();
        stringFlux.subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Completed!"));
    }

    @Test
    public void fluxTestElements_NoError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Spring Reactive");
        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Spring Reactive")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_WithError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Spring Reactive")
                .concatWith(Flux.error(new RuntimeException("Error occurred")));

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Spring Reactive")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void fluxTestElements_WithErrorMessage() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Spring Reactive")
                .concatWith(Flux.error(new RuntimeException("Error occurred")));

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring Boot", "Spring Reactive")
                .expectErrorMessage("Error occurred")
                .verify();
    }


    @Test
    public void monoTest() {
        Mono<String> monoString = Mono.just("Spring")
                .log();
        StepVerifier.create(monoString)
                .expectNext("Spring")
                .verifyComplete();
    }
}
