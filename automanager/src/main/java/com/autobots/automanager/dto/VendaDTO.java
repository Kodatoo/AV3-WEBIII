package com.autobots.automanager.dto;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

public class VendaDTO extends RepresentationModel<VendaDTO> {

    private Long id;
    private LocalDate data;
    private Double total;
    private Long clienteId;
    private Long veiculoId;

    public VendaDTO() {
    }

    public VendaDTO(Long id, LocalDate data,
            Double total, Long clienteId,
            Long veiculoId) {

        this.id = id;
        this.data = data;
        this.total = total;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
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

    public Long getClienteId() {
        return clienteId;
    }

    public Long getVeiculoId() {
        return veiculoId;
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

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }
}