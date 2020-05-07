package com.trai.quotations.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebFluxTest
public class ControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testEndpoint() {
        List<Integer> expectedValues = Arrays.asList(1, 2, 3, 4, 5);

        webTestClient
                .get().uri("/prices/test")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .consumeWith(response -> {
                    assertEquals(expectedValues, response.getResponseBody());
                });

    }

}
