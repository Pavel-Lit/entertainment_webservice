package ru.geekbrains.authservice.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  AuthRequest {
    private String username;
    private String password;

}