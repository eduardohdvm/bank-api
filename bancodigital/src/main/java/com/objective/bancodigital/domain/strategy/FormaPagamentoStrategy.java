package com.objective.bancodigital.domain.strategy;

import java.math.BigDecimal;

public interface FormaPagamentoStrategy {
    BigDecimal calcularValorFinal(BigDecimal valor);
}