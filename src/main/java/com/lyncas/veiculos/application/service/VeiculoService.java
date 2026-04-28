package com.lyncas.veiculos.application.service;

import com.lyncas.veiculos.application.usecase.command.AdicionarVeiculoCommand;
import com.lyncas.veiculos.application.usecase.command.AtualizarVeiculoCommand;
import com.lyncas.veiculos.application.usecase.query.VeiculoResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VeiculoService {

    VeiculoResult adicionar(AdicionarVeiculoCommand command);

    VeiculoResult atualizar(AtualizarVeiculoCommand command);

    VeiculoResult obterPorId(Long id);

    Page<VeiculoResult> listar(Pageable pageable);

    void excluir(Long id);
}
