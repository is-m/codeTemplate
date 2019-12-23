package com.m.study.springboot.template.webflux.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@RunWith(SpringRunner.class)
@WebFluxTest
public class WebfluxControllerTest {

    @Autowired
    private WebTestClient client;

    private static final Logger log = LoggerFactory.getLogger(WebfluxControllerTest.class);

    @Test
    public void testMono() {
        String responseBody = client.get().uri("/api/webflux/mono")
                .accept(MediaType.APPLICATION_STREAM_JSON, MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        Assert.assertNotNull(responseBody);
    }

    @Test
    public void testFlux() {
        List<String> responseBody = client.get().uri("/api/webflux/flux")
                .accept(MediaType.APPLICATION_STREAM_JSON, MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .returnResult()
                .getResponseBody();
        log.info("{}",responseBody);
        Assert.assertNotNull(responseBody);
        Assert.assertEquals(responseBody.size(), 2);
    }
}
