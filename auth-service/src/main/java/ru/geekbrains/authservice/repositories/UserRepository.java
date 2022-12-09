package ru.geekbrains.authservice.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.geekbrains.authservice.entity.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {


    @Query("select * from usr WHERE username = :name")
    Mono<User> findByUsernameWitchQuery(@Param("name") String name);
}
