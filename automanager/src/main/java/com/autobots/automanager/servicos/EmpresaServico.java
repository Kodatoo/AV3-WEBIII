package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.EmpresaDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.repositorios.EmpresaRepository;

@Service
public class EmpresaServico {

    @Autowired
    private EmpresaRepository repository;

    public List<EmpresaDTO> listar() {

        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public EmpresaDTO buscarPorId(Long id) {

        Empresa empresa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        return converterParaDTO(empresa);
    }

    public EmpresaDTO salvar(EmpresaDTO dto) {

        Empresa empresa = converterParaEntidade(dto);

        return converterParaDTO(repository.save(empresa));
    }

    public EmpresaDTO atualizar(Long id, EmpresaDTO dto) {

        Empresa empresa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());

        return converterParaDTO(repository.save(empresa));
    }

    public void deletar(Long id) {

        repository.deleteById(id);
    }

    private EmpresaDTO converterParaDTO(Empresa empresa) {

        return new EmpresaDTO(
                empresa.getId(),
                empresa.getNome(),
                empresa.getCnpj());
    }

    private Empresa converterParaEntidade(EmpresaDTO dto) {

        Empresa empresa = new Empresa();

        empresa.setId(dto.getId());
        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());

        return empresa;
    }
}