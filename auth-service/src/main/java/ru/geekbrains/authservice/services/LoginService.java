package ru.geekbrains.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.RegisterUserDto;
import ru.geekbrains.api.JwtRequest;
import ru.geekbrains.api.JwtResponse;
import ru.geekbrains.authservice.config.utils.JwtUtil;
import ru.geekbrains.authservice.config.utils.PBKDF2Encoder;
import ru.geekbrains.authservice.converters.RegisterUserConverter;
import ru.geekbrains.authservice.entity.User;
import ru.geekbrains.authservice.repositories.UserRepository;

@Service
public class LoginService implements ReactiveUserDetailsService {
    private final UserRepository userRepository;
    private RegisterUserConverter registerUserConverter;
    private final PBKDF2Encoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginService(UserRepository userRepository, RegisterUserConverter registerUserConverter, PBKDF2Encoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.registerUserConverter = registerUserConverter;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .cast(UserDetails.class);
    }


    public Mono<ResponseEntity<JwtResponse>> acceptLogin(JwtRequest req) {
        return findByUsername(req.getUsername())
                .cast(User.class)
                .filter(userDetails -> passwordEncoder.encode(req.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new JwtResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));

    }

    public Mono<ServerResponse> addNewUser(RegisterUserDto registerUserDto) {
        Mono<User> userMono = registerUserConverter.dtoToUserEntity(registerUserDto);
        return userMono.flatMap(user -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON).body(userRepository.save(user), User.class));
    }
}
