package com.autobots.automanager.dto;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumeracoes.TipoVeiculo;

public class VeiculoDTO extends RepresentationModel<VeiculoDTO> {

    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private Integer ano;
    private Long proprietarioId;
    private TipoVeiculo tipo;

    public VeiculoDTO() {
    }

    public VeiculoDTO(Long id, String placa, String modelo,
            String marca, Integer ano, Long proprietarioId, TipoVeiculo tipo) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.proprietarioId = proprietarioId;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getAno() {
        return ano;
    }

    public Long getProprietarioId() {
        return proprietarioId;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setProprietarioId(Long proprietarioId) {
        this.proprietarioId = proprietarioId;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }
}