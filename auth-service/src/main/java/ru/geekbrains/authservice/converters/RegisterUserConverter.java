package ru.geekbrains.authservice.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.api.Dto.RegisterUserDto;
import ru.geekbrains.authservice.entity.User;
import ru.geekbrains.authservice.entity.UserRole;

@Component
@RequiredArgsConstructor
public class RegisterUserConverter {


    public User dtoToUserEntity(RegisterUserDto registerUserDto){
        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(registerUserDto.getPassword());
        user.setRole(UserRole.ROLE_USER);
        return user;
    }
}
