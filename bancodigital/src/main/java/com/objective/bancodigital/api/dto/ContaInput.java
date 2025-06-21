package com.objective.bancodigital.api.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ContaInput {
    private Long numeroConta;
    private BigDecimal saldo;
}

