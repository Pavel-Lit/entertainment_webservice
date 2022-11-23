package ru.geekbrains.coreservice.converters;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.geekbrains.coreservice.dto.ContentDto;
import ru.geekbrains.coreservice.entities.Contents;

@Component
public class ContentConverter {

    public ContentDto entityToDto(Contents contents){
        ContentDto contentDto = new ContentDto();
        contentDto.setId(contents.getId());
        contentDto.setContent(contents.getContent());
        contentDto.setTitle(contents.getContent());
        return contentDto;
    }
}
