package com.objective.bancodigital.domain.strategy;

public class FormaPagamentoFactory {
    public static FormaPagamentoStrategy getStrategy(String tipo) {
        switch (tipo.toUpperCase()) {
            case "D": return new DebitoStrategy();
            case "C": return new CreditoStrategy();
            case "P": return new PixStrategy();
            default: throw new IllegalArgumentException("Forma de pagamento inválida: " + tipo);
        }
    }
}
