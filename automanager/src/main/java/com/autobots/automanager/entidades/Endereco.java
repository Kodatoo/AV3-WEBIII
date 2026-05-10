package com.autobots.automanager.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = true)
	private String estado;
	@Column(nullable = false)
	private String cidade;
	@Column(nullable = true)
	private String bairro;
	@Column(nullable = false)
	private String rua;
	@Column(nullable = false)
	private String numero;
	@Column(nullable = true)
	private String codigoPostal;
	@Column(unique = false, nullable = true)
	private String informacoesAdicionais;

	public Long getId() {
		return id;
	}

	public String getEstado() {
		return estado;
	}

	public String getCidade() {
		return cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}

}