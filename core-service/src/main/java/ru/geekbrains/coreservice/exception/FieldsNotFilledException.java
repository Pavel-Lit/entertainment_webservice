package ru.geekbrains.coreservice.exception;

public class FieldsNotFilledException extends RuntimeException{
    public FieldsNotFilledException(String message) {
        super(message);
    }
}
