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

import com.autobots.automanager.dto.TelefoneDTO;
import com.autobots.automanager.servicos.TelefoneServico;


@RestController
@RequestMapping("/telefones")
public class TelefoneControle {

    @Autowired
    private TelefoneServico service;

    @GetMapping
    public CollectionModel<TelefoneDTO> listar() {
        List<TelefoneDTO> telefones = service.listar().stream().map(t -> {
            t.add(linkTo(methodOn(TelefoneControle.class).buscarPorId(t.getId())).withSelfRel());
            return t;
        }).collect(Collectors.toList());

        return CollectionModel.of(telefones,
                linkTo(methodOn(TelefoneControle.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public TelefoneDTO buscarPorId(@PathVariable Long id) {
        TelefoneDTO t = service.buscarPorId(id);

        t.add(linkTo(methodOn(TelefoneControle.class).buscarPorId(id)).withSelfRel());
        t.add(linkTo(methodOn(TelefoneControle.class).listar()).withRel("telefones"));
        t.add(linkTo(methodOn(TelefoneControle.class).deletar(id)).withRel("deletar"));

        return t;
    }

    @PostMapping
    public TelefoneDTO salvar(@RequestBody TelefoneDTO dto) {
        TelefoneDTO t = service.salvar(dto);

        t.add(linkTo(methodOn(TelefoneControle.class).buscarPorId(t.getId())).withSelfRel());

        return t;
    }

    @PutMapping("/{id}")
    public TelefoneDTO atualizar(@PathVariable Long id, @RequestBody TelefoneDTO dto) {
        TelefoneDTO t = service.atualizar(id, dto);

        t.add(linkTo(methodOn(TelefoneControle.class).buscarPorId(id)).withSelfRel());

        return t;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
}
}