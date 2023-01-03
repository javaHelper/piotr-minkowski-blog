package com.piomin.controller;

import com.piomin.model.Person;
import com.piomin.util.DataGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private WebClient client;

    @Autowired
    private DataGeneration dataGeneration;

    @GetMapping("/json")
    public Flux<Person> findPersonsJson() {
        return Flux.fromStream(() -> dataGeneration.prepareStream())
                .doOnNext(person -> LOGGER.info("Server Produces : {}", person));
    }

    @GetMapping(value = "/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Person> findPersonsStream() {
        return Flux.fromStream(() -> dataGeneration.prepareStream())
                .delaySequence(Duration.ofMillis(100))
                .doOnNext(person -> LOGGER.info("Server produces: {}", person));
    }


    @GetMapping(value = "/stream/back-pressure", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Person> findPersonsStreamBackPressure() {
        return Flux.fromStream(() -> dataGeneration.prepareStream())
                .delayElements(Duration.ofMillis(100))
                .doOnNext(person -> LOGGER.info("Server produces: {}", person));
    }

    @GetMapping("/integration/{param}")
    public Flux<Person> findPersonsIntegration(@PathVariable("param") String param) {
        return Flux.fromStream(() -> dataGeneration.prepareStreamPart1())
                .log()
                .mergeWith(
                        client.get()
                                .uri("/slow/" + param)
                                .retrieve()
                                .bodyToFlux(Person.class)
                                .log()
                );
    }


    @GetMapping("/integration-in-different-pool/{param}")
    public Flux<Person> findPersonsIntegrationInDifferentPool(@PathVariable("param") String param) {
        return Flux.fromStream(() -> dataGeneration.prepareStreamPart1())
                .log()
                .mergeWith(
                        client.get().uri("/slow/" + param)
                                .retrieve()
                                .bodyToFlux(Person.class)
                                .log()
                                .publishOn(Schedulers.fromExecutor(taskExecutor))
                );
    }
}