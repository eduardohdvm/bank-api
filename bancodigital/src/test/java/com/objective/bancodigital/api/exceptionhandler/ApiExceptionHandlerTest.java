package com.objective.bancodigital.api.exceptionhandler;

import com.objective.bancodigital.domain.exceptions.ContaJaExisteException;
import com.objective.bancodigital.domain.exceptions.ContaNaoEncontradaException;
import com.objective.bancodigital.domain.exceptions.SaldoInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;

class ApiExceptionHandlerTest {

    private ApiExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ApiExceptionHandler();
    }

    @Test
    void shouldHandleContaJaExisteException() {
        ContaJaExisteException ex = new ContaJaExisteException("Conta já existe");
        ResponseEntity<Object> response = handler.handleContaJaExiste(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Conta já existe"));
    }

    @Test
    void shouldHandleContaNaoEncontradaException() {
        ContaNaoEncontradaException ex = new ContaNaoEncontradaException("Conta não encontrada");
        ResponseEntity<Object> response = handler.handleContaNaoEncontrada(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Conta não encontrada"));
    }

    @Test
    void shouldHandleSaldoInsuficienteException() {
        SaldoInsuficienteException ex = new SaldoInsuficienteException("Saldo insuficiente");
        ResponseEntity<Object> response = handler.handleSaldoInsuficiente(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Saldo insuficiente"));
    }
}
