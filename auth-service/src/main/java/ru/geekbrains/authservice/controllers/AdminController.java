package ru.geekbrains.authservice.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.UserDto;
import ru.geekbrains.authservice.entity.User;
import ru.geekbrains.authservice.services.AdminService;

@RestController
@RequestMapping("/administrate")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public Flux<User> list(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "10") Long count
    ) {
        return adminService.listUsers().skip(start).take(count);
    }

    @PutMapping("/modifyRoles")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> updateRoleUser(@RequestBody UserDto user) {
        return adminService.updateRoleByUsername(user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Long id) {
        return adminService.deleteById(id);
    }
}