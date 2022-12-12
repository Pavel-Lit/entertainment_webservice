package ru.geekbrains.coreservice.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.coreservice.entities.Contents;


public interface ContentRepository extends ReactiveCrudRepository<Contents, Long> {

    @Query(value = "select contents.id, contents.content, categories.title from contents " +
            "join categories on category_id = categories.id " +
            "where moderate = true order by contents.id;")
    Flux<Contents> findAllModerateContent();

    @Query(value = "select contents.id, contents.content, categories.title from contents " +
            "join categories on category_id = categories.id " +
            "where moderate = false order by contents.id;")
    Flux<Contents> findAllUnmoderateContent();

    @Query(value = "update contents set moderate =true where id = $1")
    Mono<Void> update(Long id);



    @Query(value = "INSERT INTO contents (content,category_id, moderate) values ( $1, $2, FALSE)")
    Mono<Void> saveContentWithQuery(String content, int content_id);

    @Query(value = "DELETE FROM contents WHERE id = $1")
    Mono<Void> deleteContents(Long id);
}