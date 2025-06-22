package com.objective.bancodigital.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Conta {
    @Id
    private Long numeroConta;
    private BigDecimal saldo;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> transacoes;
}

