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

import com.autobots.automanager.dto.EnderecoDTO;
import com.autobots.automanager.servicos.EnderecoServico;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControle {

    @Autowired
    private EnderecoServico service;

    @GetMapping
    public CollectionModel<EnderecoDTO> listar() {
        List<EnderecoDTO> enderecos = service.listar().stream().map(e -> {
            e.add(linkTo(methodOn(EnderecoControle.class).buscarPorId(e.getId())).withSelfRel());
            return e;
        }).collect(Collectors.toList());

        return CollectionModel.of(enderecos,
                linkTo(methodOn(EnderecoControle.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EnderecoDTO buscarPorId(@PathVariable Long id) {
        EnderecoDTO e = service.buscarPorId(id);

        e.add(linkTo(methodOn(EnderecoControle.class).buscarPorId(id)).withSelfRel());
        e.add(linkTo(methodOn(EnderecoControle.class).listar()).withRel("enderecos"));
        e.add(linkTo(methodOn(EnderecoControle.class).deletar(id)).withRel("deletar"));

        return e;
    }

    @PostMapping
    public EnderecoDTO salvar(@RequestBody EnderecoDTO dto) {
        EnderecoDTO e = service.salvar(dto);

        e.add(linkTo(methodOn(EnderecoControle.class).buscarPorId(e.getId())).withSelfRel());

        return e;
    }

    @PutMapping("/{id}")
    public EnderecoDTO atualizar(@PathVariable Long id, @RequestBody EnderecoDTO dto) {
        EnderecoDTO e = service.atualizar(id, dto);

        e.add(linkTo(methodOn(EnderecoControle.class).buscarPorId(id)).withSelfRel());

        return e;
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
}
}