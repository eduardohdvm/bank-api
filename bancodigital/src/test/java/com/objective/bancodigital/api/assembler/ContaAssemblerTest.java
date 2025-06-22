package com.objective.bancodigital.api.assembler;

import com.objective.bancodigital.api.dto.ContaInput;
import com.objective.bancodigital.api.dto.ContaModel;
import com.objective.bancodigital.domain.model.Conta;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaAssemblerTest {

    private ContaAssembler assembler = new ContaAssembler(new ModelMapper());

    @Test
    void shouldConvertInputToEntity() {
        ContaInput input = new ContaInput();
        input.setNumeroConta(123L);
        input.setSaldo(new BigDecimal("500.00"));

        Conta conta = assembler.toEntity(input);
        assertEquals(123L, conta.getNumeroConta());
        assertEquals(new BigDecimal("500.00"), conta.getSaldo());
    }

    @Test
    void shouldConvertEntityToModel() {
        Conta conta = new Conta();
        conta.setNumeroConta(456L);
        conta.setSaldo(new BigDecimal("700.00"));

        ContaModel model = assembler.toModel(conta);
        assertEquals(456L, model.getNumeroConta());
        assertEquals(new BigDecimal("700.00"), model.getSaldo());
    }
}
