package com.fullquackdeveloper.consumerservice.web;

import com.fullquackdeveloper.consumerservice.client.HelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/greeting")
@RequiredArgsConstructor
public class GreetingController {

    @Autowired
    Environment environment;

    private final HelloClient helloClient;
    @GetMapping("/{name}")
    public String greetHello (@PathVariable String name)
    {
        String port = environment.getProperty("local.server.port");
        return helloClient.sayHello(name) + " from " + port;
    }
}
