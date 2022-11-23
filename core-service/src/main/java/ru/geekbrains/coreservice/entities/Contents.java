package ru.geekbrains.coreservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@NoArgsConstructor
@Data
public class Contents {

    @Id
    private Long id;
    private String content;
    private String title;
    private boolean moderate;


}
