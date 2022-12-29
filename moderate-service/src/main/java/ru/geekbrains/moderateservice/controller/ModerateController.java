package ru.geekbrains.moderateservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.geekbrains.api.Dto.ContentDto;
import ru.geekbrains.moderateservice.service.ModerateService;


@RestController
@RequestMapping()
@RequiredArgsConstructor
@Tag(name = "Контент", description = "Методы работы с контентом")
public class ModerateController {
    private final ModerateService moderateService;

    @Operation(
            summary = "Получение списка отмодерированного контента",
            responses = {
                    @ApiResponse(
                            description = "Успешний ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ContentDto.class))
                    )
            }
    )
    @GetMapping()
    public Flux<ContentDto> getContent(@RequestParam(name = "page", defaultValue = "1") Long page){
        return moderateService.AllUnModerateContent(page);
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
    public void moderateContent(@PathVariable("id") Long id){
         moderateService.fromModerateContent(id);
    }


}
