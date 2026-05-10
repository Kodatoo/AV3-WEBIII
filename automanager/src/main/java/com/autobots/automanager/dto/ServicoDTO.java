package com.autobots.automanager.dto;

import org.springframework.hateoas.RepresentationModel;

public class ServicoDTO extends RepresentationModel<ServicoDTO> {

    private Long id;
    private String descricao;
    private Double valor;

    public ServicoDTO() {
    }

    public ServicoDTO(Long id, String descricao, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}