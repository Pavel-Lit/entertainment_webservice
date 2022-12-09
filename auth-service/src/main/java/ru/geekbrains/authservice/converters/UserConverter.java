package ru.geekbrains.authservice.converters;
import org.springframework.stereotype.Component;
import ru.geekbrains.api.Dto.UserDto;
import ru.geekbrains.authservice.entity.User;


@Component
public class UserConverter {
    public UserDto entityToDto(User u) {
        UserDto userDto = new UserDto();
        userDto.setUsername(u.getUsername());
        return userDto;
    }
}
