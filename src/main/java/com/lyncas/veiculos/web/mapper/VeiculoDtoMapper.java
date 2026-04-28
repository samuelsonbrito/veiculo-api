package com.lyncas.veiculos.web.mapper;

import com.lyncas.veiculos.application.usecase.command.AdicionarVeiculoCommand;
import com.lyncas.veiculos.application.usecase.command.AtualizarVeiculoCommand;
import com.lyncas.veiculos.application.usecase.query.VeiculoResult;
import com.lyncas.veiculos.web.dto.VeiculoDto;
import org.springframework.stereotype.Component;

@Component
public class VeiculoDtoMapper {

    public AdicionarVeiculoCommand toAdicionarCommand(VeiculoDto.Request request) {
        return new AdicionarVeiculoCommand(
                request.descricao(),
                request.marca(),
                request.modelo(),
                request.opcionais(),
                request.valor()
        );
    }

    public AtualizarVeiculoCommand toAtualizarCommand(Long id, VeiculoDto.Request request) {
        return new AtualizarVeiculoCommand(
                id,
                request.descricao(),
                request.marca(),
                request.modelo(),
                request.opcionais(),
                request.valor()
        );
    }

    public VeiculoDto.Response toResponse(VeiculoResult result) {
        return new VeiculoDto.Response(
                result.id(),
                result.descricao(),
                result.marca(),
                result.modelo(),
                result.opcionais(),
                result.valor()
        );
    }
}
