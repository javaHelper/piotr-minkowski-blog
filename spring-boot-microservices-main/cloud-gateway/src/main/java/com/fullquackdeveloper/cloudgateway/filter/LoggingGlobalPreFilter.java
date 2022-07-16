package com.fullquackdeveloper.cloudgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingGlobalPreFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        log.info("Request path = " + request.getPath());
        request.getHeaders().forEach((k, v) -> log.info(k + ": " + v));

        ServerHttpRequest mutatedRequest =
                exchange.getRequest().mutate().header("x-custom", "x-custom-value").build();
        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

        return chain.filter(mutatedExchange);
    }
}
