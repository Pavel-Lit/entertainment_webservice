package ru.geekbrains.moderateservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.moderateservice.dto.ContentDto;
import ru.geekbrains.moderateservice.integration.CoreServiceIntegration;


@RestController
@RequestMapping("/api/v1/moder")
@RequiredArgsConstructor
public class ModerateController {
    private final CoreServiceIntegration coreServiceIntegration;


    @GetMapping("/")
    public Flux<ContentDto> get(){
        return coreServiceIntegration.getAllUnmoderateContent();
    }

    @GetMapping("/m/{id}")
    public void moderateContent(@PathVariable("id") Long id){
         coreServiceIntegration.moderateContent(id);
    }
}
