package com.objective.bancodigital.domain.strategy;

import com.objective.bancodigital.domain.model.Conta;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FormaPagamentoFactoryTest {

    @Test
    void shouldApplyPixWithoutFee() {
        Conta conta = new Conta();
        conta.setNumeroConta(1L);
        conta.setSaldo(new BigDecimal("100.00"));

        new PixStrategy().processar(conta, new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"), conta.getSaldo());
    }

    @Test
    void shouldApplyDebitoWithFee() {
        Conta conta = new Conta();
        conta.setNumeroConta(2L);
        conta.setSaldo(new BigDecimal("100.00"));

        new DebitoStrategy().processar(conta, new BigDecimal("20.00"));
        assertEquals(new BigDecimal("79.40"), conta.getSaldo());
    }

    @Test
    void shouldApplyCreditoWithFee() {
        Conta conta = new Conta();
        conta.setNumeroConta(3L);
        conta.setSaldo(new BigDecimal("100.00"));

        new CreditoStrategy().processar(conta, new BigDecimal("20.00"));
        assertEquals(new BigDecimal("79.00"), conta.getSaldo());
    }

    @Test
    void shouldThrowIfInsufficientBalance() {
        Conta conta = new Conta();
        conta.setNumeroConta(4L);
        conta.setSaldo(new BigDecimal("10.00"));

        assertThrows(RuntimeException.class, () -> {
            new DebitoStrategy().processar(conta, new BigDecimal("20.00"));
        });
    }
}
