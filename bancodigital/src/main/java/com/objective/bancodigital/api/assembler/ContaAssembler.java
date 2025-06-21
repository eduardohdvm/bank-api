package com.objective.bancodigital.api.assembler;

import com.objective.bancodigital.api.dto.ContaInput;
import com.objective.bancodigital.api.dto.ContaModel;
import com.objective.bancodigital.domain.model.Conta;
import org.springframework.stereotype.Component;

@Component
public class ContaAssembler {

    public Conta toEntity(ContaInput input) {
        Conta conta = new Conta();
        conta.setNumeroConta(input.getNumeroConta());
        conta.setSaldo(input.getSaldo());
        return conta;
    }

    public ContaModel toModel(Conta conta) {
        ContaModel model = new ContaModel();
        model.setNumeroConta(conta.getNumeroConta());
        model.setSaldo(conta.getSaldo());
        return model;
    }
}

