package com.objective.bancodigital.application.service;

import com.objective.bancodigital.api.dto.TransacaoInput;
import com.objective.bancodigital.domain.exceptions.ContaJaExisteException;
import com.objective.bancodigital.domain.exceptions.ContaNaoEncontradaException;
import com.objective.bancodigital.domain.exceptions.SaldoInsuficienteException;
import com.objective.bancodigital.domain.model.Conta;
import com.objective.bancodigital.domain.repository.ContaRepository;
import com.objective.bancodigital.domain.strategy.FormaPagamentoFactory;
import com.objective.bancodigital.domain.strategy.FormaPagamentoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;

    public Conta criarConta(Conta conta) {
        if (contaRepository.existsById(conta.getNumeroConta())) {
            throw new ContaJaExisteException("Conta já existente");
        }
        return contaRepository.save(conta);
    }

    public Conta buscarConta(Long numeroConta) {
        return contaRepository.findById(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
    }

    public Conta realizarTransacao(TransacaoInput dto) {
        Conta conta = buscarConta(dto.getNumeroConta());

        FormaPagamentoStrategy strategy = FormaPagamentoFactory.getStrategy(dto.getFormaPagamento());
        BigDecimal valorFinal = strategy.calcularValorFinal(dto.getValor());

        if (conta.getSaldo().compareTo(valorFinal) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo().subtract(valorFinal));
        return contaRepository.save(conta);
    }
}





