package com.autobots.automanager.dto;

import org.springframework.hateoas.RepresentationModel;

public class CredencialDTO extends RepresentationModel<CredencialDTO> {

    private Long id;
    private String login;
    private String senha;
    private Long usuarioId;

    public CredencialDTO() {
    }

    public CredencialDTO(Long id, String login,
            String senha, Long usuarioId) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}