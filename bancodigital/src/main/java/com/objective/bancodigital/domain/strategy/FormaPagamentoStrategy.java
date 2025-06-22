package com.objective.bancodigital.domain.strategy;

import com.objective.bancodigital.domain.model.Conta;

import java.math.BigDecimal;

public interface FormaPagamentoStrategy {
    void processar(Conta conta, BigDecimal valor);

    default BigDecimal calcularValorFinal(BigDecimal valor) {
        return valor;
    }
}