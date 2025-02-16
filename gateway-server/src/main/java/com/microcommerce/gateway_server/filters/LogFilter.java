package com.microcommerce.gateway_server.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LogFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getURI().toString();
        log.info("**** Requête interceptée ! L'URL est : {}", requestUrl);

        return chain.filter(exchange); // Continue le traitement de la requête
    }

    @Override
    public int getOrder() {
        return -1; // Priorité élevée pour s'exécuter en premier
    }
}

