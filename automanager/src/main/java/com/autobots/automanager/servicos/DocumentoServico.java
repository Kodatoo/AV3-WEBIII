package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DocumentoDTO;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@Service
public class DocumentoServico {

    @Autowired
    private DocumentoRepositorio repositorio;

    public List<DocumentoDTO> listar() {
        return repositorio.findAll()
                .stream()
                .map(this::converterDocumentoDTO)
                .collect(Collectors.toList());
    }

    public DocumentoDTO buscarPorId(Long id) {
        Documento doc = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));
        return converterDocumentoDTO(doc);
    }

    public DocumentoDTO salvar(DocumentoDTO dto) {
        Documento doc = converterDocumentoEntidade(dto);
        return converterDocumentoDTO(repositorio.save(doc));
    }

    public DocumentoDTO atualizar(Long id, DocumentoDTO dto) {
        Documento doc = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        doc.setTipo(dto.getTipo());
        doc.setNumero(dto.getNumero());

        return converterDocumentoDTO(repositorio.save(doc));
    }

    public void deletar(Long id) {
        Documento doc = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        repositorio.delete(doc);
    }

    private DocumentoDTO converterDocumentoDTO(Documento doc) {
        DocumentoDTO dto = new DocumentoDTO();
        dto.setId(doc.getId());
        dto.setTipo(doc.getTipo());
        dto.setNumero(doc.getNumero());
        return dto;
    }

    private Documento converterDocumentoEntidade(DocumentoDTO dto) {
        Documento doc = new Documento();
        doc.setId(dto.getId());
        doc.setTipo(dto.getTipo());
        doc.setNumero(dto.getNumero());
        return doc;
    }
}