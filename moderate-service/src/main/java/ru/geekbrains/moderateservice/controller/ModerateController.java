package ru.geekbrains.moderateservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.moderateservice.service.ModerateService;


@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ModerateController {
    private final ModerateService moderateService;


    @GetMapping()
    public Flux<ContentDto> getContent(){
        return moderateService.AllUnModerateContent();
    }

    @GetMapping("/{id}")
    public void moderateContent(@PathVariable("id") Long id){
         moderateService.fromModerateContent(id);
    }
}
