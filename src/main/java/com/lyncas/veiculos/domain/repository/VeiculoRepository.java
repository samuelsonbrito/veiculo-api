package com.lyncas.veiculos.domain.repository;

import com.lyncas.veiculos.domain.entity.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VeiculoRepository {

    Veiculo save(Veiculo veiculo);

    Optional<Veiculo> findById(Long id);

    Page<Veiculo> findAll(Pageable pageable);

    boolean existsById(Long id);

    void deleteById(Long id);
}
