package ru.geekbrains.coreservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Likes {

    private Long id;
    private Long content_id;
    private String username;
}
