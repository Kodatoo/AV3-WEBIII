package com.autobots.automanager.excecao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AutoManagerExcecao {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> tratarValidacao(MethodArgumentNotValidException ex,
            HttpServletResponse response) {

        response.setStatus(HttpStatus.BAD_REQUEST.value());

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.put(error.getField(), error.getDefaultMessage());
        });

        return erros;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> tratarBanco(DataIntegrityViolationException ex,
            HttpServletResponse response) {

        response.setStatus(HttpStatus.CONFLICT.value());

        Map<String, String> erro = new HashMap<>();
        erro.put("erro", "Não é possível executar a operação. Existem dados relacionados.");
        return erro;
    }

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> tratarRuntime(RuntimeException ex,
            HttpServletResponse response) {

        response.setStatus(HttpStatus.NOT_FOUND.value());

        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        return erro;
    }
}