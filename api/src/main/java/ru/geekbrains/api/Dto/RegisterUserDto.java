package ru.geekbrains.api.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель для регистрации пользователей")
public class RegisterUserDto {
    @Schema(description = "Имя пользователя", required = true, example = "Bob", maxLength = 64)
    private String username;
    @Schema(description = "Пароль", required = true, example = "qwerty", maxLength = 64)
    private String password;
    @Schema(description = "Подтверждение пароля", required = true, example = "qwerty", maxLength = 64)
    private String confirmPassword;

    public RegisterUserDto(String username, String password, String confirmPassword, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;

    }

    public RegisterUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
