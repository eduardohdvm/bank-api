package com.objective.bancodigital.domain.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Conta {
    @Id
    private Long numeroConta;
    private BigDecimal saldo;
}

