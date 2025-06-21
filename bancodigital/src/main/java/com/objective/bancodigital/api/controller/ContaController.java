package com.objective.bancodigital.api.controller;

import com.objective.bancodigital.api.assembler.ContaAssembler;
import com.objective.bancodigital.api.dto.ContaInput;
import com.objective.bancodigital.api.dto.ContaModel;
import com.objective.bancodigital.api.dto.TransacaoInput;
import com.objective.bancodigital.application.service.ContaService;
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
        // implementar
        return null;
    }

    @GetMapping
    public ResponseEntity<ContaModel> buscarConta(@RequestParam Long numero_conta) {
        // implementar
        return null;
    }

    @PostMapping("/transacao")
    public ResponseEntity<ContaModel> realizarTransacao(@RequestBody TransacaoInput transacaoInput) {
        // implementar
        return null;
    }
}
