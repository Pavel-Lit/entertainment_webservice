package ru.geekbrains.moderateservice.integration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.geekbrains.api.Dto.ContentDto;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final WebClient coreServiceWebClient;

    public Flux<ContentDto> getAllUnModerateContent(){
        return coreServiceWebClient.get()
                .uri("/api/v1/mem/unmoderate")
                .retrieve()
                .bodyToFlux(ContentDto.class).onErrorStop();
    }

    public void moderateContent(Long id) {
         coreServiceWebClient.get()
                .uri("/api/v1/mem/"+id)
                .retrieve()
                .toBodilessEntity().subscribe();
        System.out.println("ok");
    }
}
