package com.objective.bancodigital.api.controller;

import com.objective.bancodigital.api.assembler.ContaAssembler;
import com.objective.bancodigital.api.dto.ContaInput;
import com.objective.bancodigital.api.dto.TransacaoInput;
import com.objective.bancodigital.application.service.ContaService;
import com.objective.bancodigital.domain.model.Conta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContaControllerIT {

    @Autowired
    private ContaService contaService;

    private ContaAssembler contaAssembler;

    @BeforeEach
    void setUp() {
        contaAssembler = new ContaAssembler(new ModelMapper());
    }

    @Test
    void shouldCreateAccountSuccessfully() {
        ContaInput input = new ContaInput();
        input.setNumeroConta(999L);
        input.setSaldo(new BigDecimal("150.00"));

        Conta conta = contaAssembler.toEntity(input);
        Conta created = contaService.criarConta(conta);

        assertNotNull(created);
        assertEquals(999L, created.getNumeroConta());
        assertEquals(new BigDecimal("150.00"), created.getSaldo());
    }

    @Test
    void shouldRetrieveExistingAccount() {
        Conta conta = new Conta();
        conta.setNumeroConta(888L);
        conta.setSaldo(new BigDecimal("300.00"));

        contaService.criarConta(conta);

        Conta found = contaService.buscarConta(888L);
        assertEquals(new BigDecimal("300.00"), found.getSaldo());
    }

    @Test
    void shouldFailIfAccountNotFound() {
        assertThrows(RuntimeException.class, () -> contaService.buscarConta(123456L));
    }

    @Test
    void shouldProcessTransactionSuccessfully() {
        Conta conta = new Conta();
        conta.setNumeroConta(777L);
        conta.setSaldo(new BigDecimal("100.00"));

        contaService.criarConta(conta);

        TransacaoInput input = new TransacaoInput();
        input.setNumeroConta(777L);
        input.setValor(new BigDecimal("50.00"));
        input.setFormaPagamento("P");

        Conta updated = contaService.realizarTransacao(input);
        assertEquals(new BigDecimal("50.00"), updated.getSaldo());
    }

    @Test
    void shouldFailTransactionDueToInsufficientBalance() {
        Conta conta = new Conta();
        conta.setNumeroConta(666L);
        conta.setSaldo(new BigDecimal("10.00"));

        contaService.criarConta(conta);

        TransacaoInput input = new TransacaoInput();
        input.setNumeroConta(666L);
        input.setValor(new BigDecimal("50.00"));
        input.setFormaPagamento("C");

        assertThrows(RuntimeException.class, () -> contaService.realizarTransacao(input));
    }
}
