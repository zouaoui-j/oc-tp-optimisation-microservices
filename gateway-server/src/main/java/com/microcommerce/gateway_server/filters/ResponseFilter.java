package com.microcommerce.gateway_server.filters;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@ConditionalOnProperty(name = "gateway-server.filters.responseFilter.enabled", havingValue = "true", matchIfMissing = true)
public class ResponseFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(ResponseFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            exchange.getResponse().setRawStatusCode(400); // Définit le code HTTP à 400
            log.info(" CODE HTTP {} ", exchange.getResponse().getStatusCode());
        }));
    }

    @Override
    public int getOrder() {
        return 1; // Priorité basse pour qu'il s'exécute après la réponse du backend
    }
}

