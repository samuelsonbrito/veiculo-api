package com.lyncas.veiculos.infra.persistence.repository;

import com.lyncas.veiculos.infra.persistence.entity.VeiculoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoJpaRepository extends JpaRepository<VeiculoJpaEntity, Long> {
}
