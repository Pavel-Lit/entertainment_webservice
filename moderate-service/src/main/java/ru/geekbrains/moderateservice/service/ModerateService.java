package ru.geekbrains.moderateservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.coreservice.repositories.ContentRepository;
import ru.geekbrains.moderateservice.integration.CoreServiceIntegration;

@Service
@RequiredArgsConstructor
public class ModerateService {
    private final CoreServiceIntegration coreServiceIntegration;
    private final static Long COUNT_MEM_FROM_PAGE = 10L;

    public Flux<ContentDto> AllUnModerateContent(Long page) {
        return coreServiceIntegration.getAllUnModerateContent()
                .skip((page - 1) * COUNT_MEM_FROM_PAGE)
                .take(COUNT_MEM_FROM_PAGE);
    }

    public void fromModerateContent(Long id) {
        coreServiceIntegration.moderateContent(id);
    }
}
