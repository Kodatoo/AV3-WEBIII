package com.autobots.automanager.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Credencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    @OneToOne
    private Usuario usuario;

    public Credencial() {
    }

    public Credencial(Long id, String login, String senha, Usuario usuario) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}