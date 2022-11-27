package ru.geekbrains.coreservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.coreservice.converters.ContentConverter;
import ru.geekbrains.coreservice.dto.ContentDto;
import ru.geekbrains.coreservice.services.ContentService;

@RestController
@RequestMapping("/api/v1/mem")
@RequiredArgsConstructor
public class ContentController {

    private final ContentConverter contentConverter;
    private final ContentService contentService;

    @GetMapping("/")
    public Flux<ContentDto> getAllModerateContent(){
        return contentService.getAllModerateContent()
                .map(contentConverter::entityToDto);

    }

    @GetMapping("/unmoderate")
    public Flux<ContentDto> get(){
        return contentService.getAllUnModerateContent().map(contentConverter::entityToDto);
    }


    @GetMapping ("/{id}")
    public Mono<Void> moderate(@PathVariable("id") Long id){
       return contentService.moderate(id);
    }
}
