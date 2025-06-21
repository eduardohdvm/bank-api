package com.objective.bancodigital.domain.strategy;

import java.math.BigDecimal;

public class PixStrategy implements FormaPagamentoStrategy {
    public BigDecimal calcularValorFinal(BigDecimal valor) {
        return valor;
    }
}
