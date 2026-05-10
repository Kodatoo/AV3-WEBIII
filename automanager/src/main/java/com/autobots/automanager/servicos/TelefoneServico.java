package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.TelefoneDTO;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@Service
public class TelefoneServico {

    @Autowired
    private TelefoneRepositorio repositorio;

    public List<TelefoneDTO> listar() {
        return repositorio.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public TelefoneDTO buscarPorId(Long id) {
        Telefone telefone = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));
        return converterParaDTO(telefone);
    }

    public TelefoneDTO salvar(TelefoneDTO dto) {
        Telefone telefone = converterTelefoneEntidade(dto);
        return converterParaDTO(repositorio.save(telefone));
    }

    public TelefoneDTO atualizar(Long id, TelefoneDTO dto) {
        Telefone telefone = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

        telefone.setDdd(dto.getDdd());
        telefone.setNumero(dto.getNumero());

        return converterParaDTO(repositorio.save(telefone));
    }

    public void deletar(Long id) {
    Telefone telefone = repositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

    repositorio.delete(telefone);
}

    private TelefoneDTO converterParaDTO(Telefone t) {
        TelefoneDTO dto = new TelefoneDTO();
        dto.setId(t.getId());
        dto.setDdd(t.getDdd());
        dto.setNumero(t.getNumero());
        return dto;
    }

    private Telefone converterTelefoneEntidade(TelefoneDTO dto) {
        Telefone t = new Telefone();
        t.setId(dto.getId());
        t.setDdd(dto.getDdd());
        t.setNumero(dto.getNumero());
        return t;
    }
}