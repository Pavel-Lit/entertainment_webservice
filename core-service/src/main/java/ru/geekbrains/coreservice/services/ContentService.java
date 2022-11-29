package ru.geekbrains.coreservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.coreservice.converters.ContentConverter;
import ru.geekbrains.coreservice.repositories.ContentRepository;

@Repository
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    private final ContentConverter contentConverter;

    public Flux<ContentDto> getAllModerateContent() {
        return contentRepository.findAllModerateContent().map(contentConverter::entityToDto);
    }

    public Flux<ContentDto> getAllUnModerateContent() {
        return contentRepository.findAllUnmoderateContent().map(contentConverter::entityToDto);
    }

    public Mono<Void> moderate(Long id) {
        return contentRepository.update(id);
    }
}
