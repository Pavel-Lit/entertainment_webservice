package ru.geekbrains.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.authservice.entity.User;
import ru.geekbrains.authservice.repositories.AdminRepository;
import ru.geekbrains.authservice.repositories.UserRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;


    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    // Не проверялось
    public Flux<User> listUsers() {
        return adminRepository.findAll();
    }

    public Mono<Void> deleteByUsername(User user) {
        return adminRepository.delete(user);
    }


    public Mono<ServerResponse> updateRoleByUsername(User user) {
        return userRepository.findByUsername(user.getUsername())
                .flatMap(u -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(adminRepository.save(user), User.class));
    }
}
