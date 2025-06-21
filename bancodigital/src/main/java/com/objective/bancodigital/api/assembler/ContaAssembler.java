package com.objective.bancodigital.api.assembler;

import com.objective.bancodigital.api.dto.ContaInput;
import com.objective.bancodigital.api.dto.ContaModel;
import com.objective.bancodigital.domain.model.Conta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContaAssembler {

    private final ModelMapper modelMapper;

    public Conta toEntity(ContaInput input) {
        return modelMapper.map(input, Conta.class);
    }

    public ContaModel toModel(Conta conta) {
        return modelMapper.map(conta, ContaModel.class);
    }
}
