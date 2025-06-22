package com.objective.bancodigital.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "numero_conta", nullable = false)
    private Conta conta;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(name = "valor_com_taxa", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorComTaxa;

    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime dataTransacao;

    @Column(name = "forma_pagamento", length = 1, nullable = false)
    private String formaPagamento;

}