package com.piomin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Value("${target.uri}")
    private String targetUri;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(targetUri).build();
    }

    @PostConstruct
    public void init() {
        LOGGER.info("CPU: {}", Runtime.getRuntime().availableProcessors());
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("slow-");
        executor.initialize();
        return executor;
    }
}