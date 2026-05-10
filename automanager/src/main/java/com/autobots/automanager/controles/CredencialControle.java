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

import com.autobots.automanager.dto.CredencialDTO;
import com.autobots.automanager.servicos.CredencialServico;

@RestController
@RequestMapping("/credenciais")
public class CredencialControle {

    @Autowired
    private CredencialServico service;

    @GetMapping
    public CollectionModel<CredencialDTO> listar() {

        List<CredencialDTO> credenciais = service.listar().stream().map(c -> {

            c.add(linkTo(methodOn(CredencialControle.class)
                    .buscarPorId(c.getId())).withSelfRel());

            return c;

        }).collect(Collectors.toList());

        return CollectionModel.of(credenciais,
                linkTo(methodOn(CredencialControle.class)
                        .listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public CredencialDTO buscarPorId(@PathVariable Long id) {

        CredencialDTO c = service.buscarPorId(id);

        c.add(linkTo(methodOn(CredencialControle.class)
                .buscarPorId(id)).withSelfRel());

        c.add(linkTo(methodOn(CredencialControle.class)
                .listar()).withRel("credenciais"));

        return c;
    }

    @PostMapping
    public CredencialDTO salvar(@RequestBody CredencialDTO dto) {

        CredencialDTO c = service.salvar(dto);

        c.add(linkTo(methodOn(CredencialControle.class)
                .buscarPorId(c.getId())).withSelfRel());

        return c;
    }

    @PutMapping("/{id}")
    public CredencialDTO atualizar(@PathVariable Long id,
            @RequestBody CredencialDTO dto) {

        CredencialDTO c = service.atualizar(id, dto);

        c.add(linkTo(methodOn(CredencialControle.class)
                .buscarPorId(id)).withSelfRel());

        return c;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}