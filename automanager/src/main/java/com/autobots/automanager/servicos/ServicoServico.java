package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.ServicoDTO;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.repositorios.ServicoRepository;

@Service
public class ServicoServico {

    @Autowired
    private ServicoRepository repository;

    public List<ServicoDTO> listar() {

        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ServicoDTO buscarPorId(Long id) {

        Servico servico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        return converterParaDTO(servico);
    }

    public ServicoDTO salvar(ServicoDTO dto) {

        Servico servico = converterParaEntidade(dto);

        return converterParaDTO(repository.save(servico));
    }

    public ServicoDTO atualizar(Long id, ServicoDTO dto) {

        Servico servico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        servico.setDescricao(dto.getDescricao());
        servico.setValor(dto.getValor());

        return converterParaDTO(repository.save(servico));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private ServicoDTO converterParaDTO(Servico servico) {

        return new ServicoDTO(
                servico.getId(),
                servico.getDescricao(),
                servico.getValor());
    }

    private Servico converterParaEntidade(ServicoDTO dto) {

        Servico servico = new Servico();

        servico.setId(dto.getId());
        servico.setDescricao(dto.getDescricao());
        servico.setValor(dto.getValor());

        return servico;
    }
}