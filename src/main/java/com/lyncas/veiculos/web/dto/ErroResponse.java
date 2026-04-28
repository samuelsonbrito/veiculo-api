package com.lyncas.veiculos.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "ErroResponse", description = "Resposta de erro da API")
public record ErroResponse(

        @Schema(description = "Timestamp do erro")
        LocalDateTime timestamp,

        @Schema(description = "Código HTTP do erro", example = "400")
        int status,

        @Schema(description = "Mensagem geral do erro", example = "Erro de validação")
        String mensagem,

        @Schema(description = "Lista de erros de campo")
        List<CampoErro> erros
) {
    @Schema(name = "CampoErro", description = "Erro de validação de campo")
    public record CampoErro(
            @Schema(description = "Nome do campo com erro", example = "descricao")
            String campo,
            @Schema(description = "Mensagem de erro do campo", example = "Descrição é obrigatória")
            String mensagem
    ) {}
}
