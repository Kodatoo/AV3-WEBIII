package com.autobots.automanager.entidades;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private Double total;

    @ManyToOne
    private Usuario cliente;

    @ManyToOne
    private Veiculo veiculo;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens;

    public Venda() {
    }

    public Venda(Long id, LocalDate data, Double total,
            Usuario cliente, Veiculo veiculo,
            List<ItemVenda> itens) {
        this.id = id;
        this.data = data;
        this.total = total;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Double getTotal() {
        return total;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
}