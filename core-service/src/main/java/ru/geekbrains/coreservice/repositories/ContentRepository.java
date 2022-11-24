package ru.geekbrains.coreservice.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
}
