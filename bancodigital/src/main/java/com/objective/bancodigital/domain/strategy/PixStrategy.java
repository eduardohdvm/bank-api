package com.objective.bancodigital.domain.strategy;

import com.objective.bancodigital.domain.exceptions.SaldoInsuficienteException;
import com.objective.bancodigital.domain.model.Conta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PixStrategy implements FormaPagamentoStrategy {
    @Override
    public BigDecimal processar(Conta conta, BigDecimal valor) {
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        return valor;
    }
}
