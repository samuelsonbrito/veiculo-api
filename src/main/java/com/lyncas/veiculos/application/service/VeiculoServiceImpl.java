package com.lyncas.veiculos.application.service;

import com.lyncas.veiculos.application.usecase.command.AdicionarVeiculoCommand;
import com.lyncas.veiculos.application.usecase.command.AtualizarVeiculoCommand;
import com.lyncas.veiculos.application.usecase.query.VeiculoResult;
import com.lyncas.veiculos.domain.entity.Veiculo;
import com.lyncas.veiculos.domain.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @Override
    public VeiculoResult adicionar(AdicionarVeiculoCommand command) {
        Veiculo veiculo = new Veiculo(
                null,
                command.descricao(),
                command.marca(),
                command.modelo(),
                command.opcionais(),
                command.valor()
        );
        Veiculo salvo = veiculoRepository.save(veiculo);
        return VeiculoResult.from(salvo);
    }

    @Override
    public VeiculoResult atualizar(AtualizarVeiculoCommand command) {
        Veiculo existente = veiculoRepository.findById(command.id())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Veículo não encontrado com id: " + command.id()));

        existente.setDescricao(command.descricao());
        existente.setMarca(command.marca());
        existente.setModelo(command.modelo());
        existente.setOpcionais(command.opcionais());
        existente.setValor(command.valor());

        Veiculo atualizado = veiculoRepository.save(existente);
        return VeiculoResult.from(atualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public VeiculoResult obterPorId(Long id) {
        return veiculoRepository.findById(id)
                .map(VeiculoResult::from)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Veículo não encontrado com id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VeiculoResult> listar(Pageable pageable) {
        return veiculoRepository.findAll(pageable)
                .map(VeiculoResult::from);
    }

    @Override
    public void excluir(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new EntityNotFoundException("Veículo não encontrado com id: " + id);
        }
        veiculoRepository.deleteById(id);
    }
}
