package com.objective.bancodigital.domain.repository;

import com.objective.bancodigital.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
