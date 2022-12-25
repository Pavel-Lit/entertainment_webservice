package ru.geekbrains.api.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель DTO контента")
public class ContentDto {

    @Schema(description = "ID отмодерированного контента", required = true, example = "1")
    private Long id;
    @Schema(description = "Пример шутки", required = true, example = "какая то шутка")
    private String content;
    @Schema(description = "Категория", required = true, example = "Жизненно")
    private String title;
    @Schema(description = "Лайки", required = true, example = "5")
    private int likes;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public ContentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
