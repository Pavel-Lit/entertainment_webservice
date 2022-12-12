package ru.geekbrains.coreservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;


@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(ContentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorDto> handleUserNotFoundException(Exception ex) {
        return Mono.just(new ErrorDto("NOT_FOUND", ex.getMessage()));
    }


    @ExceptionHandler(FieldsNotFilledException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ErrorDto> handleFieldsNotFilledException(Exception ex) {
        return Mono.just(new ErrorDto("NO_FOUND", ex.getMessage()));
    }

}