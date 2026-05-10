package com.autobots.automanager.controles;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dto.DocumentoDTO;
import com.autobots.automanager.servicos.DocumentoServico;


@RestController
@RequestMapping("/documentos")
public class DocumentoControle {

    @Autowired
    private DocumentoServico service;

    @GetMapping
    public CollectionModel<DocumentoDTO> listar() {
        List<DocumentoDTO> documentos = service.listar().stream().map(doc -> {
            doc.add(linkTo(methodOn(DocumentoControle.class).buscarPorId(doc.getId())).withSelfRel());
            return doc;
        }).collect(Collectors.toList());

        return CollectionModel.of(documentos,
                linkTo(methodOn(DocumentoControle.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public DocumentoDTO buscarPorId(@PathVariable Long id) {
        DocumentoDTO doc = service.buscarPorId(id);

        doc.add(linkTo(methodOn(DocumentoControle.class).buscarPorId(id)).withSelfRel());
        doc.add(linkTo(methodOn(DocumentoControle.class).listar()).withRel("documentos"));
        doc.add(linkTo(methodOn(DocumentoControle.class).deletar(id)).withRel("deletar"));

        return doc;
    }

    @PostMapping
    public DocumentoDTO salvar(@RequestBody DocumentoDTO dto) {
        DocumentoDTO doc = service.salvar(dto);

        doc.add(linkTo(methodOn(DocumentoControle.class).buscarPorId(doc.getId())).withSelfRel());

        return doc;
    }

    @PutMapping("/{id}")
    public DocumentoDTO atualizar(@PathVariable Long id, @RequestBody DocumentoDTO dto) {
        DocumentoDTO doc = service.atualizar(id, dto);

        doc.add(linkTo(methodOn(DocumentoControle.class).buscarPorId(id)).withSelfRel());

        return doc;
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
}
}