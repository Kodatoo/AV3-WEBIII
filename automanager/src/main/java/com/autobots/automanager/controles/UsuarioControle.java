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

import com.autobots.automanager.dto.UsuarioDTO;
import com.autobots.automanager.servicos.UsuarioServico;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControle {

    @Autowired
    private UsuarioServico service;

    @GetMapping
    public CollectionModel<UsuarioDTO> listar() {

        List<UsuarioDTO> usuarios = service.listar().stream().map(u -> {

            u.add(linkTo(methodOn(UsuarioControle.class)
                    .buscarPorId(u.getId())).withSelfRel());

            return u;

        }).collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControle.class)
                        .listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public UsuarioDTO buscarPorId(@PathVariable Long id) {

        UsuarioDTO u = service.buscarPorId(id);

        u.add(linkTo(methodOn(UsuarioControle.class)
                .buscarPorId(id)).withSelfRel());

        u.add(linkTo(methodOn(UsuarioControle.class)
                .listar()).withRel("usuarios"));

        return u;
    }

    @PostMapping
    public UsuarioDTO salvar(@RequestBody UsuarioDTO dto) {

        UsuarioDTO u = service.salvar(dto);

        u.add(linkTo(methodOn(UsuarioControle.class)
                .buscarPorId(u.getId())).withSelfRel());

        return u;
    }

    @PutMapping("/{id}")
    public UsuarioDTO atualizar(@PathVariable Long id,
            @RequestBody UsuarioDTO dto) {

        UsuarioDTO u = service.atualizar(id, dto);

        u.add(linkTo(methodOn(UsuarioControle.class)
                .buscarPorId(id)).withSelfRel());

        return u;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}