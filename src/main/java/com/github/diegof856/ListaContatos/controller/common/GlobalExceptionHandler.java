package com.github.diegof856.ListaContatos.controller.common;

import com.github.diegof856.ListaContatos.exceptions.NotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundExceptions.class)
    private ResponseEntity<Object> notFoundHandler(NotFoundExceptions exceptions){
        Map<String, Object> body = new HashMap<>();
        body.put("Erro",exceptions.getMessage());
        body.put("Status", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

}
