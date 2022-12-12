package ru.geekbrains.coreservice.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.coreservice.entities.Contents;

@Component
public class ContentConverter {

    public ContentDto entityToDto(Contents contents){
        ContentDto contentDto = new ContentDto();
        contentDto.setId(contents.getId());
        contentDto.setContent(contents.getContent());
        contentDto.setTitle(contents.getTitle());
        return contentDto;
    }
    public Contents DtoToEntity(ContentDto dto){
        Contents contents = new Contents();
        contents.setId(dto.getId());
        contents.setContent(dto.getContent());
        contents.setTitle(dto.getTitle());
        return contents;
    }
}
