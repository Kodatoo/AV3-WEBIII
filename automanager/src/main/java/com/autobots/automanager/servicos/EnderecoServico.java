package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.EnderecoDTO;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@Service
public class EnderecoServico {

    @Autowired
    private EnderecoRepositorio repositorio;

    public List<EnderecoDTO> listar() {
        return repositorio.findAll()
                .stream()
                .map(this::converterEnderecoDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO buscarPorId(Long id) {
        Endereco endereco = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        return converterEnderecoDTO(endereco);
    }

    public EnderecoDTO salvar(EnderecoDTO dto) {
        Endereco endereco = converterEnderecoEntidaded(dto);
        return converterEnderecoDTO(repositorio.save(endereco));
    }

    public void deletar(Long id) {
    Endereco endereco = repositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

    repositorio.delete(endereco);
}

    public EnderecoDTO atualizar(Long id, EnderecoDTO dto) {
        Endereco endereco = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setBairro(dto.getBairro());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setCodigoPostal(dto.getCodigoPostal());
        endereco.setInformacoesAdicionais(dto.getInformacoesAdicionais());

        return converterEnderecoDTO(repositorio.save(endereco));
    }

    private EnderecoDTO converterEnderecoDTO(Endereco e) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(e.getId());
        dto.setCidade(e.getCidade());
        dto.setEstado(e.getEstado());
        dto.setBairro(e.getBairro());
        dto.setRua(e.getRua());
        dto.setNumero(e.getNumero());
        dto.setCodigoPostal(e.getCodigoPostal());
        dto.setInformacoesAdicionais(e.getInformacoesAdicionais());
        return dto;
    }

    private Endereco converterEnderecoEntidaded(EnderecoDTO dto) {
        Endereco e = new Endereco();
        e.setId(dto.getId());
        e.setCidade(dto.getCidade());
        e.setEstado(dto.getEstado());
        e.setBairro(dto.getBairro());
        e.setRua(dto.getRua());
        e.setNumero(dto.getNumero());
        e.setCodigoPostal(dto.getCodigoPostal());
        e.setInformacoesAdicionais(dto.getInformacoesAdicionais());
        return e;
    }
}