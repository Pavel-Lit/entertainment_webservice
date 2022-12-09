package ru.geekbrains.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.RegisterUserDto;
import ru.geekbrains.authservice.converters.RegisterUserConverter;
import ru.geekbrains.authservice.services.LoginService;


@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final LoginService loginService;
    @Autowired
    public RegistrationController(LoginService loginService, RegisterUserConverter registerUserConverter) {
        this.loginService = loginService;
    }


    @PostMapping
    public Mono<ServerResponse> registrationNewUser(@RequestBody RegisterUserDto registerUserDto) {
       return loginService.addNewUser(registerUserDto);
    }
}
