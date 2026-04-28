package com.lyncas.veiculos.web.dto;

import com.lyncas.veiculos.domain.enumerator.Marca;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class VeiculoDto {

    @Schema(name = "VeiculoRequest", description = "Dados para cadastro ou atualização de veículo")
    public record Request(

            @Schema(description = "Descrição do veículo", example = "Sedan completo, único dono", requiredMode = Schema.RequiredMode.REQUIRED)
            @NotBlank(message = "Descrição é obrigatória")
            @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
            String descricao,

            @Schema(description = "Marca do veículo", example = "TOYOTA", requiredMode = Schema.RequiredMode.REQUIRED)
            @NotNull(message = "Marca é obrigatória")
            Marca marca,

            @Schema(description = "Modelo do veículo", example = "Corolla XEi", requiredMode = Schema.RequiredMode.REQUIRED)
            @NotBlank(message = "Modelo é obrigatório")
            @Size(max = 100, message = "Modelo deve ter no máximo 100 caracteres")
            String modelo,

            @Schema(description = "Itens opcionais do veículo", example = "Ar condicionado, direção elétrica, rodas de liga", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
            @Size(max = 500, message = "Opcionais deve ter no máximo 500 caracteres")
            String opcionais,

            @Schema(description = "Valor do veículo em reais", example = "89900.00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
            @Positive(message = "Valor deve ser positivo")
            BigDecimal valor
    ) {}

    @Schema(name = "VeiculoResponse", description = "Dados de resposta de um veículo")
    public record Response(

            @Schema(description = "Identificador único do veículo", example = "1")
            Long id,

            @Schema(description = "Descrição do veículo", example = "Sedan completo, único dono")
            String descricao,

            @Schema(description = "Marca do veículo", example = "TOYOTA")
            Marca marca,

            @Schema(description = "Modelo do veículo", example = "Corolla XEi")
            String modelo,

            @Schema(description = "Itens opcionais do veículo", example = "Ar condicionado, direção elétrica")
            String opcionais,

            @Schema(description = "Valor do veículo em reais", example = "89900.00")
            BigDecimal valor
    ) {}
}
