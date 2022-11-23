package ru.geekbrains.coreservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
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
}
