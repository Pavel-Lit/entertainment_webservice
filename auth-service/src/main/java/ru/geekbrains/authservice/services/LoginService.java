package ru.geekbrains.authservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.RegisterUserDto;
import ru.geekbrains.api.JwtRequest;
import ru.geekbrains.api.JwtResponse;
import ru.geekbrains.authservice.exception.UserAlreadyExistsException;
import ru.geekbrains.authservice.exception.UserNotFoundException;
import ru.geekbrains.authservice.config.utils.JwtUtil;
import ru.geekbrains.authservice.config.utils.PBKDF2Encoder;
import ru.geekbrains.authservice.converters.RegisterUserConverter;
import ru.geekbrains.authservice.entity.User;
import ru.geekbrains.authservice.entity.UserRole;
import ru.geekbrains.authservice.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService implements ReactiveUserDetailsService {
    private final UserRepository userRepository;
    private final PBKDF2Encoder passwordEncoder;
    private final RegisterUserConverter converter;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
//        return userRepository.findByUsername(username)
//                .cast(UserDetails.class);
        return userRepository.findByUsernameWitchQuery(username).switchIfEmpty(
                Mono.error(new UserNotFoundException(username))
        ).cast(UserDetails.class);
    }

    public Mono<ResponseEntity<JwtResponse>> acceptLogin(JwtRequest req) {
        return findByUsername(req.getUsername())
                .cast(User.class)
                .filter(userDetails -> passwordEncoder.encode(req.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new JwtResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.error(() -> new UserNotFoundException("USER NOT FOUND")));

    }

    public Mono addNewUser(RegisterUserDto UserDto) {
        User user = convertDtoToUser(UserDto);
        user.setRole(UserRole.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.findByUsernameWitchQuery(user.getUsername())
                .flatMap((el) -> Mono.error(new UserAlreadyExistsException(user.getUsername())))
                .switchIfEmpty(Mono.defer(() -> userRepository.save(user)));
    }

    private User convertDtoToUser(RegisterUserDto registerUserDto) {
        return converter.dtoToUserEntity(registerUserDto);
    }
}
