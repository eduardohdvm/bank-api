package com.objective.bancodigital.domain.strategy;

import com.objective.bancodigital.domain.model.Conta;

import java.math.BigDecimal;

public interface FormaPagamentoStrategy {
    BigDecimal processar(Conta conta, BigDecimal valor);

}