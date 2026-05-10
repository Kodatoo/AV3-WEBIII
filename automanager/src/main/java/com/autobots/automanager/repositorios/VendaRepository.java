package com.autobots.automanager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entidades.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}