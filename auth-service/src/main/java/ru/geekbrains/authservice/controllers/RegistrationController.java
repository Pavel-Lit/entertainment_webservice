package ru.geekbrains.authservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.RegisterUserDto;
import ru.geekbrains.authservice.services.LoginService;


@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
@Tag(name = "Регистрация", description = "Методы для работы с регистрацией")
public class RegistrationController {
    private final LoginService loginService;

    @Operation(summary = "Регистрация нового пользователя",
            responses = {
                    @ApiResponse(description = "Новый пользователь зарегистрирован", responseCode = "201"),
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono registrationNewUser(@RequestBody RegisterUserDto registerUserDto) {
      return loginService.addNewUser(registerUserDto);
    }
}
