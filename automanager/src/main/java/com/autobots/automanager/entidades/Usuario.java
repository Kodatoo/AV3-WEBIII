package com.autobots.automanager.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.autobots.automanager.enumeracoes.TipoUsuario;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @ManyToOne
    private Empresa empresa;

    public Usuario() {
    }

    public Usuario(Long id, String nome, TipoUsuario tipoUsuario, Empresa empresa) {
        this.id = id;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        this.empresa = empresa;
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

    public Empresa getEmpresa() {
        return empresa;
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

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}