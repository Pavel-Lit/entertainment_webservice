package ru.geekbrains.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.JwtRequest;
import ru.geekbrains.api.JwtResponse;
import ru.geekbrains.authservice.services.LoginService;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<JwtResponse>> login(@RequestBody JwtRequest req) {
        return loginService.acceptLogin(req);
    }
}
