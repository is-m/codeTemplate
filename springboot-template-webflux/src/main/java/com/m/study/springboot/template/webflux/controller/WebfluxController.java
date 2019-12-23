package com.m.study.springboot.template.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

// MimeTypeUtils
@RestController
@RequestMapping(path = "/api/webflux"/*, produces = MediaType.APPLICATION_STREAM_JSON_VALUE*/)
public class WebfluxController {

    @GetMapping("/mono")
    public Mono<String> mono() {
        return Mono.just("hello mono");
    }

    @GetMapping("/flux")
    public Flux<String> flux() {
        return Flux.fromStream(Arrays.asList("hello", "world").stream());
    }


}
