package com.lyncas.veiculos.infra.persistence.repository;

import com.lyncas.veiculos.domain.entity.Veiculo;
import com.lyncas.veiculos.domain.repository.VeiculoRepository;
import com.lyncas.veiculos.infra.persistence.mapper.VeiculoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VeiculoRepositoryImpl implements VeiculoRepository {

    private final VeiculoJpaRepository jpaRepository;
    private final VeiculoMapper mapper;

    public VeiculoRepositoryImpl(VeiculoJpaRepository jpaRepository, VeiculoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Veiculo save(Veiculo veiculo) {
        var jpaEntity = mapper.toJpaEntity(veiculo);
        var salvo = jpaRepository.save(jpaEntity);
        return mapper.toDomain(salvo);
    }

    @Override
    public Optional<Veiculo> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<Veiculo> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(mapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
