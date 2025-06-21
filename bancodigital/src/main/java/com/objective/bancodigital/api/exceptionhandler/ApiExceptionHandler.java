package com.objective.bancodigital.api.exceptionhandler;

import com.objective.bancodigital.domain.exceptions.SaldoInsuficienteException;
import com.objective.bancodigital.domain.exceptions.ContaJaExisteException;
import com.objective.bancodigital.domain.exceptions.ContaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ContaJaExisteException.class)
    public ResponseEntity<Object> handleContaJaExiste(ContaJaExisteException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<Object> handleContaNaoEncontrada(ContaNaoEncontradaException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<Object> handleSaldoInsuficiente(SaldoInsuficienteException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", OffsetDateTime.now());
        body.put("status", status.value());
        body.put("error", message);
        return ResponseEntity.status(status).body(body);
    }
}
