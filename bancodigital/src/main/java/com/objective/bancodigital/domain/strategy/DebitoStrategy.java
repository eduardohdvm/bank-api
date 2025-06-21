package com.objective.bancodigital.domain.strategy;

import java.math.BigDecimal;

public class DebitoStrategy implements FormaPagamentoStrategy {
    public BigDecimal calcularValorFinal(BigDecimal valor) {
        return valor.add(valor.multiply(new BigDecimal("0.03")));
    }
}
