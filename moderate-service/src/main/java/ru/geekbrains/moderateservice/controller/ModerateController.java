package ru.geekbrains.moderateservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.coreservice.services.ContentService;
import ru.geekbrains.moderateservice.service.ModerateService;


@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ModerateController {
    private final ModerateService moderateService;


    @GetMapping()
    public Flux<ContentDto> getContent(@RequestParam(name = "page", defaultValue = "1") Long page){
        return moderateService.AllUnModerateContent(page);
    }

    @GetMapping("/{id}")
    public void moderateContent(@PathVariable("id") Long id){
         moderateService.fromModerateContent(id);
    }


}
