package com.objective.bancodigital.application.service;

import com.objective.bancodigital.api.dto.TransacaoInput;
import com.objective.bancodigital.domain.exceptions.ContaJaExisteException;
import com.objective.bancodigital.domain.exceptions.ContaNaoEncontradaException;
import com.objective.bancodigital.domain.exceptions.SaldoInsuficienteException;
import com.objective.bancodigital.domain.model.Conta;
import com.objective.bancodigital.domain.repository.ContaRepository;
import com.objective.bancodigital.domain.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ContaServiceTest {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @BeforeEach
    void setUp() {
        transacaoRepository.deleteAll();
        contaRepository.deleteAll();
    }

    @Test
    void shouldCreateAccount() {
        Conta conta = new Conta();
        conta.setNumeroConta(123L);
        conta.setSaldo(new BigDecimal("100.00"));

        Conta created = contaService.criarConta(conta);

        assertNotNull(created);
        assertEquals(123L, created.getNumeroConta());
        assertEquals(new BigDecimal("100.00"), created.getSaldo());
    }

    @Test
    void shouldThrowIfAccountAlreadyExists() {
        Conta conta = new Conta();
        conta.setNumeroConta(1002L);
        conta.setSaldo(new BigDecimal("100.00"));
        contaService.criarConta(conta);

        assertThrows(ContaJaExisteException.class, () -> contaService.criarConta(conta));
    }

    @Test
    void shouldReturnAccountWhenExists() {
        Conta conta = new Conta();
        conta.setNumeroConta(1003L);
        conta.setSaldo(new BigDecimal("200.00"));
        contaService.criarConta(conta);

        Conta found = contaService.buscarConta(1003L);
        assertEquals(new BigDecimal("200.00"), found.getSaldo());
    }

    @Test
    void shouldThrowWhenAccountNotFound() {
        assertThrows(ContaNaoEncontradaException.class, () -> contaService.buscarConta(9999L));
    }

    @Test
    void shouldProcessPixTransaction() {
        Conta conta = new Conta();
        conta.setNumeroConta(1004L);
        conta.setSaldo(new BigDecimal("100.00"));
        contaService.criarConta(conta);

        TransacaoInput input = new TransacaoInput();
        input.setNumeroConta(1004L);
        input.setFormaPagamento("P");
        input.setValor(new BigDecimal("50.00"));

        Conta result = contaService.realizarTransacao(input);
        assertEquals(new BigDecimal("50.00"), result.getSaldo());
    }

    @Test
    void shouldThrowIfInsufficientBalance() {
        Conta conta = new Conta();
        conta.setNumeroConta(1005L);
        conta.setSaldo(new BigDecimal("20.00"));
        contaService.criarConta(conta);

        TransacaoInput input = new TransacaoInput();
        input.setNumeroConta(1005L);
        input.setFormaPagamento("C");
        input.setValor(new BigDecimal("50.00"));

        assertThrows(SaldoInsuficienteException.class, () -> contaService.realizarTransacao(input));
    }
}
