package ru.geekbrains.authservice.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
            @RequestParam(defaultValue = "3") Long count
    ) {
        return adminService.listUsers().skip(start).take(count);
    }

//    @GetMapping("/deleteUserByUsername")
//    public Mono<Void> deleteUserByUsername(@RequestParam User user) {
//        return adminService.deleteByUsername(user);
//    }
    @PostMapping
    public Mono<ServerResponse> addUser(@RequestBody User user) {
        return adminService.updateRoleByUsername(user);
    }
}