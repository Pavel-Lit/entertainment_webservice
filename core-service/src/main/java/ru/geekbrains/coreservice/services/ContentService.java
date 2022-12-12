package ru.geekbrains.coreservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.coreservice.converters.ContentConverter;
import ru.geekbrains.coreservice.entities.Contents;
import ru.geekbrains.coreservice.exception.ContentNotFoundException;
import ru.geekbrains.coreservice.repositories.ContentRepository;

import javax.swing.text.AbstractDocument;

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

    //java.lang.NumberFormatException: null необходимо обработать ошибку
    public Mono<Void> addContent(ContentDto contentDto) {
        Contents contents = contentConverter.DtoToEntity(contentDto);
        return contentRepository.saveContentWithQuery(
                contents.getContent(),
                Integer.parseInt(contents.getTitle())
        ).and(Mono.empty());
    }

    public Mono<Void> deleteContentById(Long id) {
        return contentRepository.findById(id).flatMap(e ->
                        Mono.defer(() -> contentRepository.deleteContents(id)))
                .switchIfEmpty(
                        Mono.error(
                                new ContentNotFoundException(
                                        String.format("Content with ID: %d NOT FOUND", id))));
    }
}
