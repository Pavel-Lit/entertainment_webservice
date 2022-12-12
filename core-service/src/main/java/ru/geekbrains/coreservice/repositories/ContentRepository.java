package ru.geekbrains.coreservice.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.coreservice.entities.Contents;
import ru.geekbrains.coreservice.entities.Likes;


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

    @Query(value = "update contents set likes = likes+1 where id=:id")
    void inkrementLike(Long id);

//    @Query(value = "select * from likes where username = :name and content_id = :id")
    @Query(value = "select * from likes where username = $1 and content_id = $2")
//    Likes getByUsernameAndId(@Param("name") String username, @Param("id") Long id);
    Mono<Likes> getByUsernameAndId(String username, Long id);

    @Query(value = "insert into likes (username, content_id) values ($1 , $2")
    Mono<Void> updateLike(String username, Long id);

    @Query(value = "delete from likes where username = :name and content_id = :id")
    Mono<Void> decrementLikes(@Param("name") String username, @Param("id") Long id);
}
