package ru.geekbrains.authservice.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.geekbrains.authservice.entity.User;

@Repository
public interface AdminRepository extends ReactiveCrudRepository<User, Long> {
    Mono<Void> save (User user);
}
