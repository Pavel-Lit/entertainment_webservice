package ru.geekbrains.gatewayservice.utils;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAdminFilter.Config> {
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtAuthFilter(JwtUtil jwtUtil) {
        super(JwtAdminFilter.Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(JwtAdminFilter.Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!isAuthMissing(request)) {
                final String token = getAuthHeader(request);

                if (jwtUtil.isInvalid(token)) {
                    return this.onError(exchange, "Authorization header is invalid", HttpStatus.BAD_REQUEST);
                }


              populateRequestWithHeaders(exchange, token);

            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus badRequest) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(badRequest);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey("Authorization")) {
            return true;
        }
        if (!request.getHeaders().getOrEmpty("Authorization").get(0).startsWith("Bearer ")) {
            return true;
        }
        return false;
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("username", claims.getSubject())
                .build();
    }

}