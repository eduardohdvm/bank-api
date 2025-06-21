package com.objective.bancodigital.api.assembler;

import com.objective.bancodigital.api.dto.ContaInput;
import com.objective.bancodigital.api.dto.ContaModel;
import com.objective.bancodigital.domain.model.Conta;
import org.springframework.stereotype.Component;

@Component
public class ContaAssembler {

    public Conta toEntity(ContaInput input) {
        // implementar
        return null;
    }

    public ContaModel toModel(Conta conta) {
        // implementar
        return null;
    }
}

