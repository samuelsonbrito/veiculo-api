package com.lyncas.veiculos.application.usecase.command;

import com.lyncas.veiculos.domain.enumerator.Marca;

import java.math.BigDecimal;

public record AtualizarVeiculoCommand(
        Long id,
        String descricao,
        Marca marca,
        String modelo,
        String opcionais,
        BigDecimal valor
) {}
