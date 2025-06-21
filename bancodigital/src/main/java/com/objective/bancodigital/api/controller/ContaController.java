package com.objective.bancodigital.api.controller;

import com.objective.bancodigital.api.assembler.ContaAssembler;
import com.objective.bancodigital.api.dto.ContaInput;
import com.objective.bancodigital.api.dto.ContaModel;
import com.objective.bancodigital.api.dto.TransacaoInput;
import com.objective.bancodigital.application.service.ContaService;
import com.objective.bancodigital.domain.model.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;
    private final ContaAssembler contaAssembler;

    @PostMapping
    public ResponseEntity<ContaModel> criarConta(@RequestBody ContaInput contaInput) {
        Conta novaConta = contaService.criarConta(contaAssembler.toEntity(contaInput));
        return ResponseEntity.status(201).body(contaAssembler.toModel(novaConta));
    }

    @GetMapping
    public ResponseEntity<ContaModel> buscarConta(@RequestParam Long numero_conta) {
        Conta conta = contaService.buscarConta(numero_conta);
        return ResponseEntity.ok(contaAssembler.toModel(conta));
    }

    @PostMapping("/transacao")
    public ResponseEntity<ContaModel> realizarTransacao(@RequestBody TransacaoInput transacaoInput) {
        Conta contaAtualizada = contaService.realizarTransacao(transacaoInput);
        return ResponseEntity.status(201).body(contaAssembler.toModel(contaAtualizada));
    }
}

