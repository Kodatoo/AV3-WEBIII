package com.autobots.automanager.entidades;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.autobots.automanager.enumeracoes.TipoVeiculo;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String modelo;
    private String marca;
    private Integer ano;

    @Enumerated(javax.persistence.EnumType.STRING)
    private TipoVeiculo tipo;

    @ManyToOne
    private Usuario proprietario;

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

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
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

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }
}