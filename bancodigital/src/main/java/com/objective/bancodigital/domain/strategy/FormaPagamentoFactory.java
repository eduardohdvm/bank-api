package com.objective.bancodigital.domain.strategy;

public class FormaPagamentoFactory {

    public static FormaPagamentoStrategy get(String tipo) {
        switch (tipo.toUpperCase()) {
            case "C": return new CreditoStrategy();
            case "D": return new DebitoStrategy();
            case "P": return new PixStrategy();
            default: throw new IllegalArgumentException("Forma de pagamento inválida");
        }
    }
}
