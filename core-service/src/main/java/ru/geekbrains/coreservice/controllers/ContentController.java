package ru.geekbrains.coreservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.coreservice.exception.ErrorDto;
import ru.geekbrains.coreservice.services.ContentService;

@RestController
@RequestMapping("/api/v1/mem")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/")
    public Flux<ContentDto> getAllModerateContent(){
        return contentService.getAllModerateContent();
    }

    @GetMapping("/unmoderate")
    public Flux<ContentDto> get(){
        return contentService.getAllUnModerateContent();
    }

    @GetMapping ("/{id}")
    public Mono<Void> moderate(@PathVariable("id") Long id){
       return contentService.moderate(id);
    }

    @PostMapping ("/addmem")
    public Mono<Void> addContents(@RequestBody ContentDto contentDto){
        return contentService.addContent(contentDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteContents(@PathVariable("id") Long id) {
        return contentService.deleteContentById(id);
    }
}

