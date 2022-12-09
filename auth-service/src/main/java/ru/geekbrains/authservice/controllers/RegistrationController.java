package ru.geekbrains.authservice.controllers;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RegistrationController {
    private final LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono registrationNewUser(@RequestBody RegisterUserDto registerUserDto) {
      return loginService.addNewUser(registerUserDto);
    }
}
