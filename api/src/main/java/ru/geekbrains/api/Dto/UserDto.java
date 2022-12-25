package ru.geekbrains.api.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель пользователя")
public class UserDto {
    @Schema(description = "ID пользователя", required = true, example = "1")
    private Long id;
    @Schema(description = "Имя пользователя", required = true, maxLength = 64, example = "David")
    private String username;
    @Schema(description = "Роль пользователя", required = true, maxLength = 64, example = "ROLE_USER")
    private String role;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
