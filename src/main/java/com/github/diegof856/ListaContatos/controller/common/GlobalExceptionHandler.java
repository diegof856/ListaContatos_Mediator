package com.github.diegof856.ListaContatos.controller.common;

import com.github.diegof856.ListaContatos.commands.dto.ErrorDetail;
import com.github.diegof856.ListaContatos.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundExceptions.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<Object> notFoundHandler(NotFoundExceptions exceptions){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody(exceptions.getMessage(),HttpStatus.NOT_FOUND));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private ResponseEntity<List<Map<String,Object>>> validationFieldsHandler(MethodArgumentNotValidException exceptions){
        List<Map<String, Object>> fieldErrors = exceptions.getFieldErrors().stream().map(this::errorsList).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(fieldErrors);
    }
    @ExceptionHandler(ValidateFieldsCustomizerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> invalidNumber(ValidateFieldsCustomizerException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getErrors());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> genericErrors(RuntimeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody("Ocorreu um erro inesperada. Entre em contato com a administração",HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private Map<String,Object> errorBody(Object message ,Object httpStatus){
        Map<String, Object> body = new HashMap<>();
        body.put("Erro",message);
        body.put("Status", httpStatus);
        return body;
    }
    private Map<String,Object> errorsList(FieldError error){
        Map<String, Object> body = new HashMap<>();
        body.put("Campo",error.getField());
        body.put("Erro", error.getDefaultMessage());
        body.put("Status", HttpStatus.UNPROCESSABLE_ENTITY.value());
       return body;
    }

}
