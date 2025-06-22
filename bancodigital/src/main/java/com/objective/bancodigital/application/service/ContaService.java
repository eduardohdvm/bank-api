package com.objective.bancodigital.application.service;

import com.objective.bancodigital.api.dto.TransacaoInput;
import com.objective.bancodigital.domain.exceptions.ContaJaExisteException;
import com.objective.bancodigital.domain.exceptions.ContaNaoEncontradaException;
import com.objective.bancodigital.domain.exceptions.SaldoInsuficienteException;
import com.objective.bancodigital.domain.model.Conta;
import com.objective.bancodigital.domain.model.Transacao;
import com.objective.bancodigital.domain.repository.ContaRepository;
import com.objective.bancodigital.domain.repository.TransacaoRepository;
import com.objective.bancodigital.domain.strategy.FormaPagamentoFactory;
import com.objective.bancodigital.domain.strategy.FormaPagamentoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    @Transactional
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

    @Transactional
    public Conta realizarTransacao(TransacaoInput dto) {
        Conta conta = buscarConta(dto.getNumeroConta());

        FormaPagamentoStrategy strategy = FormaPagamentoFactory.getStrategy(dto.getFormaPagamento());
        BigDecimal valorComTaxa = strategy.processar(conta, dto.getValor());

        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(dto.getValor());
        transacao.setValorComTaxa(valorComTaxa);
        transacao.setFormaPagamento(dto.getFormaPagamento());
        transacao.setDataTransacao(LocalDateTime.now());

        transacaoRepository.save(transacao);

        return conta;
    }
}