package ru.geekbrains.coreservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.api.Dto.UserDto;
import ru.geekbrains.coreservice.services.ContentService;

@RestController
@RequestMapping("/api/v1/mem")
@RequiredArgsConstructor
@Tag(name = "Контент", description = "Методы работы с контентом")
public class ContentController {
    private final ContentService contentService;

    @Operation(
            summary = "Получение списка отмодерированного контента",
            responses = {
                    @ApiResponse(
                            description = "Успешний ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ContentDto.class))
                    )
            }
    )
    @GetMapping("/")
    public Flux<ContentDto> getAllModerateContent(@RequestParam(name = "page", defaultValue = "1") Long page) {
        return contentService.getAllModerateContent(page);
    }
    @Operation(
            summary = "Получение списка контента на модерацию",
            responses = {
                    @ApiResponse(
                            description = "Успешний ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ContentDto.class))
                    )
            }
    )
    @GetMapping("/unmoderate")
    public Flux<ContentDto> get() {
        return contentService.getAllUnModerateContent();
    }
    @Operation(
            summary = "Модерация контента по ID",
            responses = {
                    @ApiResponse(
                            description = "Успешний ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{id}")
    public Mono<Void> moderate(@PathVariable("id") Long id) {
        return contentService.moderate(id);

    }
    @Operation(
            summary = "Установка/снятие лайка по ID",
            responses = {
                    @ApiResponse(
                            description = "Успешний ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/likes/{id}")
    public Mono<Void> setLikeToDb(@PathVariable("id") Long content_id, @RequestHeader String username) {
        return contentService.setLike(content_id, username);
    }
    @Operation(
            summary = "Отправка пользовательской шутки на модерацию",
            responses = {
                    @ApiResponse(
                            description = "Успешний ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ContentDto.class))
                    )
            }
    )
    @PostMapping("/addmem")
    public Mono<Void> addContents(@RequestBody ContentDto contentDto, @RequestHeader String username) {
        return contentService.addContent(contentDto);
    }
    @Operation(
            summary = "Удаление контента по ID",
            responses = {
                    @ApiResponse(
                            description = "Успешний ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public Mono<Void> deleteContents(@PathVariable("id") Long id) {
        return contentService.deleteContentById(id);
    }
}
