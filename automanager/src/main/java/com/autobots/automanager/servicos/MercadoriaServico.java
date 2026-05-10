package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.MercadoriaDTO;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.repositorios.MercadoriaRepository;

@Service
public class MercadoriaServico {

    @Autowired
    private MercadoriaRepository repository;

    public List<MercadoriaDTO> listar() {

        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public MercadoriaDTO buscarPorId(Long id) {

        Mercadoria mercadoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mercadoria não encontrada"));

        return converterParaDTO(mercadoria);
    }

    public MercadoriaDTO salvar(MercadoriaDTO dto) {

        Mercadoria mercadoria = converterParaEntidade(dto);

        return converterParaDTO(repository.save(mercadoria));
    }

    public MercadoriaDTO atualizar(Long id, MercadoriaDTO dto) {

        Mercadoria mercadoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mercadoria não encontrada"));

        mercadoria.setNome(dto.getNome());
        mercadoria.setValor(dto.getValor());
        mercadoria.setQuantidade(dto.getQuantidade());

        return converterParaDTO(repository.save(mercadoria));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private MercadoriaDTO converterParaDTO(Mercadoria mercadoria) {

        return new MercadoriaDTO(
                mercadoria.getId(),
                mercadoria.getNome(),
                mercadoria.getValor(),
                mercadoria.getQuantidade());
    }

    private Mercadoria converterParaEntidade(MercadoriaDTO dto) {

        Mercadoria mercadoria = new Mercadoria();

        mercadoria.setId(dto.getId());
        mercadoria.setNome(dto.getNome());
        mercadoria.setValor(dto.getValor());
        mercadoria.setQuantidade(dto.getQuantidade());

        return mercadoria;
    }
}