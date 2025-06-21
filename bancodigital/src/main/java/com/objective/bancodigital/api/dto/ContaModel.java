package com.objective.bancodigital.api.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ContaModel {
    private Long numeroConta;
    private BigDecimal saldo;
}

