package ru.geekbrains.coreservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.coreservice.entities.Contents;
import ru.geekbrains.coreservice.repositories.ContentRepository;

@Repository
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public Flux<Contents> getAllModerateContent(){
        return contentRepository.findAllModerateContent();
    }

    public Flux<Contents> getAllUnModerateContent(){
        return contentRepository.findAllUnmoderateContent();
    }

    public Mono<Void> moderate(Long id){
       return contentRepository.update(id);
    }
}
