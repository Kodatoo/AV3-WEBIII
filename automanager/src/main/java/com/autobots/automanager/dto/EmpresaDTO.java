package com.autobots.automanager.dto;

import org.springframework.hateoas.RepresentationModel;

public class EmpresaDTO extends RepresentationModel<EmpresaDTO> {

    private Long id;
    private String nome;
    private String cnpj;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Long id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}