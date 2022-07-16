package com.fullquackdeveloper.consumerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("producer-service")
public interface HelloClient {
    @GetMapping("/hello/{name}")
    String sayHello(@PathVariable String name);
}
