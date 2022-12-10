package ru.geekbrains.authservice.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.api.Dto.UserDto;
import ru.geekbrains.authservice.entity.User;
import ru.geekbrains.authservice.entity.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class UserConverter {
    public UserDto entityToDto(User u) {
        UserDto userDto = new UserDto();
        userDto.setUsername(u.getUsername());
        return userDto;
    }

    public User DtoToEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setRole(UserRole.ROLE_ADMIN);
        return user;
    }
}
