package com.example.projeto.controllers.exceptions;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> dataMethod(MethodArgumentNotValidException e, HttpServletRequest request) {

    Map<String, String> errors = new HashMap<>();
    e.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  
  @ExceptionHandler({ResponseStatusException.class, AccessDeniedException.class })
    public ResponseEntity<Object> notAdmin(ResponseStatusException ex, HttpServletRequest request) {
        return new ResponseEntity<Object>("Acesso não permitido", new HttpHeaders(), HttpStatus.FORBIDDEN);
  }

}
