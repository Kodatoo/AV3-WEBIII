package com.autobots.automanager.dto;

import org.springframework.hateoas.RepresentationModel;

public class MercadoriaDTO extends RepresentationModel<MercadoriaDTO> {

    private Long id;
    private String nome;
    private Double valor;
    private Integer quantidade;

    public MercadoriaDTO() {
    }

    public MercadoriaDTO(Long id, String nome,
            Double valor, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}