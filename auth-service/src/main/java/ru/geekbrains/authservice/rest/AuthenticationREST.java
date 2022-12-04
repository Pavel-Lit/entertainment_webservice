package ru.geekbrains.authservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import ru.geekbrains.authservice.handlers.AuthRequest;
import ru.geekbrains.authservice.handlers.AuthResponse;
import ru.geekbrains.authservice.security.JWTUtil;
import ru.geekbrains.authservice.security.PBKDF2Encoder;
import ru.geekbrains.authservice.service.UserService;

@AllArgsConstructor
@RestController
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;

    @PostMapping("/api/v1/auth-service")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userService.findByUsername(ar.getUsername())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}