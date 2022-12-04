package ru.geekbrains.moderateservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.geekbrains.coreservice.Dto.ContentDto;
import ru.geekbrains.moderateservice.integration.CoreServiceIntegration;

@Service
@RequiredArgsConstructor
public class ModerateService {
    private final CoreServiceIntegration coreServiceIntegration;

    public Flux<ContentDto> AllUnModerateContent() {
        return coreServiceIntegration.getAllUnModerateContent();
    }

    public void fromModerateContent(Long id) {
        coreServiceIntegration.moderateContent(id);
    }


}
