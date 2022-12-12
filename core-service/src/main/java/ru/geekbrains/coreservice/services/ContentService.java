package ru.geekbrains.coreservice.services;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.api.Dto.LikesDto;
import ru.geekbrains.coreservice.converters.ContentConverter;
import ru.geekbrains.coreservice.converters.LikesConverter;
import ru.geekbrains.coreservice.entities.Likes;
import ru.geekbrains.coreservice.repositories.ContentRepository;

@Repository
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    private final ContentConverter contentConverter;

//    private final LikesConverter likesConverter;

    public Flux<ContentDto> getAllModerateContent() {
        return contentRepository.findAllModerateContent().map(contentConverter::entityToDto);
    }

    public Flux<ContentDto> getAllUnModerateContent() {
        return contentRepository.findAllUnmoderateContent().map(contentConverter::entityToDto);
    }

    public Mono<Void> moderate(Long id) {
        return contentRepository.update(id);
    }

    public Mono<Void> setLike(Long id, String username) {
//        Mono<Likes> likes = contentRepository.getByUsernameAndId(username, id)
//                .as();
        Likes likes = new Likes();
        if(likes == null){
            contentRepository.updateLike(username, id);
            contentRepository.inkrementLike(id);
        } else {
            contentRepository.decrementLikes(username, id);
        }

















//        if(likes == null){
//            contentRepository.updateLike(username, id).subscribe();
//            contentRepository.inkrementLike(id).subscribe();
//        }else {
//            contentRepository.decrementLikes(username, id).subscribe();
//        }
//       return Mono.empty();
        return Mono.empty();
    }
}
