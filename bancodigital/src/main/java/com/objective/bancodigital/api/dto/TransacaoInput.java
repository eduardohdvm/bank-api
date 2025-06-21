package com.objective.bancodigital.api.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransacaoInput {
    private String formaPagamento;
    private Long numeroConta;
    private BigDecimal valor;
}

