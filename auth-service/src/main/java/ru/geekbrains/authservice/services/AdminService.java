package ru.geekbrains.authservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.UserDto;
import ru.geekbrains.authservice.converters.UserConverter;
import ru.geekbrains.authservice.entity.User;
import ru.geekbrains.authservice.entity.UserRole;
import ru.geekbrains.authservice.repositories.AdminRepository;
import ru.geekbrains.authservice.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final UserConverter converter;

    public Flux<User> listUsers() {
        return adminRepository.findAll();
    }

    public Mono<Void> deleteById(Long id) {
        return userRepository
                .deleteById(id)
                .and(Mono.empty());
    }


    public Mono<Void> updateRoleByUsername(UserDto user) {
        return adminRepository.updateRoleUserWithQuery(
                        user.getRole(),
                        user.getUsername())
                .and(Mono.empty());

    }
}
