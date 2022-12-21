package ru.geekbrains.coreservice.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.coreservice.entities.Contents;
import ru.geekbrains.coreservice.entities.Likes;


public interface ContentRepository extends ReactiveCrudRepository<Contents, Long> {

    @Query(value = "select contents.id, contents.content, categories.title, contents.like_count" +
            " from contents join categories on category_id = categories.id where" +
            " moderate = true order by contents.id;")
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



    @Query(value = "SELECT l.username FROM likes l WHERE l.username = $1 AND l.content_id = $2;")
    Mono<Likes> getByUsernameAndIdFromLikes(String name,
                                            Long content_id);


    @Query(value = "insert into likes (username, content_id) values (:name , :cont_id)")
    Mono<Void> addUserToLikes(@Param("name") String username,
                              @Param("cont_id") Long content_id);

    @Query(value = "delete from likes where username = :name and content_id = :id")
    Mono<Void> deleteUsernameFromLikes(@Param("name") String username,
                                        @Param("id") Long content_id);

    @Query(value = "update contents set like_count = like_count + 1 from likes l where contents.id = :cont_id;")
    Mono<Void> updateCountLikeUp(@Param("cont_id") Long content_id);

    @Query(value = "update contents set like_count = like_count - 1 from likes l where contents.id = :cont_id;")
    Mono<Void> updateCountLikeDown(@Param("cont_id") Long content_id);



}