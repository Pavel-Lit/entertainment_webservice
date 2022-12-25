package ru.geekbrains.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на авторизацию")
public class JwtRequest {
    @Schema(description = "Имя пользователя", required = true, example = "User")
    private String username;
    @Schema(description = "Пароль пользователя", required = true, example = "qwerty")
    private String password;

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

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}