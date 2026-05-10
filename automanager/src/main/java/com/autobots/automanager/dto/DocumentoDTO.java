package com.autobots.automanager.dto;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumeracoes.TipoDocumento;

public class DocumentoDTO extends RepresentationModel<DocumentoDTO> {

    private Long id;
    private TipoDocumento tipo;
    private String numero;

    public DocumentoDTO() {
    }

    public Long getId() {
        return id;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}