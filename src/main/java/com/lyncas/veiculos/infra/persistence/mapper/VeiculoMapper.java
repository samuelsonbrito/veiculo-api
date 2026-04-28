package com.lyncas.veiculos.infra.persistence.mapper;

import com.lyncas.veiculos.domain.entity.Veiculo;
import com.lyncas.veiculos.infra.persistence.entity.VeiculoJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    public VeiculoJpaEntity toJpaEntity(Veiculo domain) {
        VeiculoJpaEntity entity = new VeiculoJpaEntity();
        entity.setId(domain.getId());
        entity.setDescricao(domain.getDescricao());
        entity.setMarca(domain.getMarca());
        entity.setModelo(domain.getModelo());
        entity.setOpcionais(domain.getOpcionais());
        entity.setValor(domain.getValor());
        return entity;
    }

    public Veiculo toDomain(VeiculoJpaEntity entity) {
        return new Veiculo(
                entity.getId(),
                entity.getDescricao(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getOpcionais(),
                entity.getValor()
        );
    }
}
