package ru.geekbrains.authservice.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.UserDto;
import ru.geekbrains.authservice.entity.User;
import ru.geekbrains.authservice.services.AdminService;

@RestController
@RequestMapping("/administrate")
@RequiredArgsConstructor
@Tag(name = "Администрирование пользователей", description = "Панель администрирования пользователей")
public class AdminController {
    private final AdminService adminService;
    @Operation(
            summary = "Получение списка имеющихся пользователей",
            responses = {
                    @ApiResponse(
                            description = "Успешний ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDto.class))
                    )
            }
    )
    @GetMapping
    public Flux<User> list(@RequestParam(defaultValue = "1") Long page) {
        return adminService.listUsers(page);
    }
    @Operation(
            summary = "Изменение роли пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "202"
                    )
            }
    )
    @PostMapping("/modifyRoles")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> updateRoleUser(@RequestBody UserDto user) {
        return adminService.updateRoleByUsername(user);
    }

    @Operation(
            summary = "Удаление пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Long id) {
        return adminService.deleteById(id);
    }
}