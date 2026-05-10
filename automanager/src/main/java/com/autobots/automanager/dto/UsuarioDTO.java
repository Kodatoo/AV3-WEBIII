package com.autobots.automanager.dto;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumeracoes.TipoUsuario;

public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    private Long id;
    private String nome;
    private TipoUsuario tipoUsuario;
    private Long empresaId;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, TipoUsuario tipoUsuario, Long empresaId) {
        this.id = id;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        this.empresaId = empresaId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }
}