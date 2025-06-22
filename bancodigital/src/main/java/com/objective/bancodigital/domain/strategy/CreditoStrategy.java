package com.objective.bancodigital.domain.strategy;

import com.objective.bancodigital.domain.exceptions.SaldoInsuficienteException;
import com.objective.bancodigital.domain.model.Conta;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditoStrategy implements FormaPagamentoStrategy {
    private static final BigDecimal TAXA_CREDITO = new BigDecimal("0.05");

    @Override
    public BigDecimal processar(Conta conta, BigDecimal valor) {
        BigDecimal valorComTaxa = valor.multiply(BigDecimal.ONE.add(TAXA_CREDITO))
                .setScale(2, RoundingMode.HALF_UP);

        if (conta.getSaldo().compareTo(valorComTaxa) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para transação de crédito");
        }
        conta.setSaldo(conta.getSaldo().subtract(valorComTaxa));

        return valorComTaxa;
    }
}