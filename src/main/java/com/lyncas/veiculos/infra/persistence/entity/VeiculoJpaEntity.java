package com.lyncas.veiculos.infra.persistence.entity;

import com.lyncas.veiculos.domain.enumerator.Marca;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "veiculo")
public class VeiculoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Marca marca;

    @Column(nullable = false, length = 100)
    private String modelo;

    @Column(length = 500)
    private String opcionais;

    @Column(precision = 15, scale = 2)
    private BigDecimal valor;

    public VeiculoJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getOpcionais() { return opcionais; }
    public void setOpcionais(String opcionais) { this.opcionais = opcionais; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}
