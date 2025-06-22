package com.objective.bancodigital.domain.strategy;

import com.objective.bancodigital.domain.exceptions.SaldoInsuficienteException;
import com.objective.bancodigital.domain.model.Conta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DebitoStrategy implements FormaPagamentoStrategy {

    private static final BigDecimal TAXA = new BigDecimal("0.03");

    @Override
    public void processar(Conta conta, BigDecimal valor) {
        BigDecimal valorComTaxa = valor.add(valor.multiply(TAXA));
        if (conta.getSaldo().compareTo(valorComTaxa) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para débito com taxa");
        }
        conta.setSaldo(conta.getSaldo().subtract(valorComTaxa));
    }

}
