package oocl.example.todolist.controller;

import oocl.example.todolist.exception.TodoIllegalCreateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TodoIllegalCreateException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleTodoIllegalCreateException(Exception e) {
        return e.getMessage();
    }
}
