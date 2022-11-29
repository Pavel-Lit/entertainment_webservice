package ru.geekbrains.authservice.pero;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.geekbrains.authservice.domain.Message;

public interface MessageRepo extends ReactiveCrudRepository<Message, Long> {
}
