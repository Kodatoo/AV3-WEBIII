package com.autobots.automanager.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    private Double subtotal;

    @ManyToOne
    private Mercadoria mercadoria;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private Venda venda;

    public ItemVenda() {
    }

    public ItemVenda(Long id, Integer quantidade,
            Double subtotal, Mercadoria mercadoria,
            Servico servico, Venda venda) {
        this.id = id;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
        this.mercadoria = mercadoria;
        this.servico = servico;
        this.venda = venda;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Mercadoria getMercadoria() {
        return mercadoria;
    }

    public Servico getServico() {
        return servico;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void setMercadoria(Mercadoria mercadoria) {
        this.mercadoria = mercadoria;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}