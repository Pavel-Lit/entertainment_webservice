package ru.geekbrains.authservice.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.RegisterUserDto;
import ru.geekbrains.authservice.entity.User;
import ru.geekbrains.authservice.entity.UserRole;

@Component
@RequiredArgsConstructor
public class RegisterUserConverter {
    private final PasswordEncoder passwordEncoder;
    public Mono<User> dtoToUserEntity(RegisterUserDto registerUserDto){
        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setRole(UserRole.ROLE_USER);
        return Mono.just(user);
    }
}
