package com.objective.bancodigital.domain.strategy;

import com.objective.bancodigital.domain.exceptions.SaldoInsuficienteException;
import com.objective.bancodigital.domain.model.Conta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DebitoStrategy implements FormaPagamentoStrategy {
    private static final BigDecimal TAXA_DEBITO = new BigDecimal("0.03");

    @Override
    public BigDecimal processar(Conta conta, BigDecimal valor) {
        BigDecimal valorComTaxa = valor.multiply(BigDecimal.ONE.add(TAXA_DEBITO))
                .setScale(2, RoundingMode.HALF_UP);

        if (conta.getSaldo().compareTo(valorComTaxa) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para transação de débito");
        }

        conta.setSaldo(conta.getSaldo().subtract(valorComTaxa));
        return valorComTaxa;
    }
}
