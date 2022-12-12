package ru.geekbrains.coreservice.converters;

import ru.geekbrains.api.Dto.LikesDto;
import ru.geekbrains.coreservice.entities.Likes;

public class LikesConverter {
    public LikesDto entityToDto(Likes likes){
        LikesDto likesDto = new LikesDto();
        likesDto.setId(likes.getId());
        likesDto.setContentId(likesDto.getContentId());
        likesDto.setUsername(likesDto.getUsername());
        return likesDto;
    }
}
