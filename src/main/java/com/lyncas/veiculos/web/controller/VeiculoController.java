package com.lyncas.veiculos.web.controller;

import com.lyncas.veiculos.application.service.VeiculoService;
import com.lyncas.veiculos.application.usecase.query.VeiculoResult;
import com.lyncas.veiculos.web.dto.ErroResponse;
import com.lyncas.veiculos.web.dto.VeiculoDto;
import com.lyncas.veiculos.web.mapper.VeiculoDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veiculos")
@Tag(name = "Veículos", description = "Operações de cadastro e consulta de veículos")
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final VeiculoDtoMapper dtoMapper;

    public VeiculoController(VeiculoService veiculoService, VeiculoDtoMapper dtoMapper) {
        this.veiculoService = veiculoService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar veículo", description = "Cadastra um novo veículo no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema(implementation = ErroResponse.class)))
    })
    public ResponseEntity<VeiculoDto.Response> adicionar(@Valid @RequestBody VeiculoDto.Request request) {
        VeiculoResult result = veiculoService.adicionar(dtoMapper.toAdicionarCommand(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.toResponse(result));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar veículo", description = "Atualiza os dados de um veículo existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema(implementation = ErroResponse.class))),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado", content = @Content(schema = @Schema(implementation = ErroResponse.class)))
    })
    public ResponseEntity<VeiculoDto.Response> atualizar(
            @Parameter(description = "Identificador do veículo", required = true, example = "1")
            @PathVariable Long id,
            @Valid @RequestBody VeiculoDto.Request request) {

        VeiculoResult result = veiculoService.atualizar(dtoMapper.toAtualizarCommand(id, request));
        return ResponseEntity.ok(dtoMapper.toResponse(result));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter veículo por ID", description = "Consulta um veículo específico pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado", content = @Content(schema = @Schema(implementation = ErroResponse.class)))
    })
    public ResponseEntity<VeiculoDto.Response> obterPorId(
            @Parameter(description = "Identificador do veículo", required = true, example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(dtoMapper.toResponse(veiculoService.obterPorId(id)));
    }

    @GetMapping
    @Operation(summary = "Listar veículos", description = "Lista todos os veículos cadastrados com suporte a paginação")
    @ApiResponse(responseCode = "200", description = "Lista de veículos")
    public ResponseEntity<Page<VeiculoDto.Response>> listar(
            @ParameterObject @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        return ResponseEntity.ok(veiculoService.listar(pageable).map(dtoMapper::toResponse));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir veículo", description = "Remove um veículo do sistema pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Veículo removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado", content = @Content(schema = @Schema(implementation = ErroResponse.class)))
    })
    public ResponseEntity<Void> excluir(
            @Parameter(description = "Identificador do veículo", required = true, example = "1")
            @PathVariable Long id) {

        veiculoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
