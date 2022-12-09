package ru.geekbrains.authservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {

    private String code;
    private String data;

    public Message(String code, String data) {
        this.code = code;
        this.data = data;
    }
}