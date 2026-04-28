package com.lyncas.veiculos.application.usecase.query;

import com.lyncas.veiculos.domain.entity.Veiculo;
import com.lyncas.veiculos.domain.enumerator.Marca;

import java.math.BigDecimal;

public record VeiculoResult(
        Long id,
        String descricao,
        Marca marca,
        String modelo,
        String opcionais,
        BigDecimal valor
) {
    public static VeiculoResult from(Veiculo veiculo) {
        return new VeiculoResult(
                veiculo.getId(),
                veiculo.getDescricao(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getOpcionais(),
                veiculo.getValor()
        );
    }
}
