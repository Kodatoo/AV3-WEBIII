package com.autobots.automanager.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.autobots.automanager.enumeracoes.TipoDocumento;

@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(javax.persistence.EnumType.STRING)
	@Column
	private TipoDocumento tipo;

	@Column(unique = true)
	private String numero;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public TipoDocumento getTipo() {
		return tipo;
	}

	public String getNumero() {
		return numero;
	}

	public Cliente getCliente() {
		return cliente;
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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}