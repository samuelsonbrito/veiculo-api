package com.lyncas.veiculos.domain.entity;

import com.lyncas.veiculos.domain.enumerator.Marca;

import java.math.BigDecimal;

public class Veiculo {

    private Long id;
    private String descricao;
    private Marca marca;
    private String modelo;
    private String opcionais;
    private BigDecimal valor;

    public Veiculo() {}

    public Veiculo(Long id, String descricao, Marca marca, String modelo, String opcionais, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.marca = marca;
        this.modelo = modelo;
        this.opcionais = opcionais;
        this.valor = valor;
    }

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
