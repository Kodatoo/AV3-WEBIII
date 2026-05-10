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

import com.autobots.automanager.dto.ServicoDTO;
import com.autobots.automanager.servicos.ServicoServico;

@RestController
@RequestMapping("/servicos")
public class ServicoControle {

    @Autowired
    private ServicoServico service;

    @GetMapping
    public CollectionModel<ServicoDTO> listar() {

        List<ServicoDTO> servicos = service.listar().stream().map(s -> {

            s.add(linkTo(methodOn(ServicoControle.class)
                    .buscarPorId(s.getId())).withSelfRel());

            return s;

        }).collect(Collectors.toList());

        return CollectionModel.of(servicos,
                linkTo(methodOn(ServicoControle.class)
                        .listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ServicoDTO buscarPorId(@PathVariable Long id) {

        ServicoDTO s = service.buscarPorId(id);

        s.add(linkTo(methodOn(ServicoControle.class)
                .buscarPorId(id)).withSelfRel());

        s.add(linkTo(methodOn(ServicoControle.class)
                .listar()).withRel("servicos"));

        return s;
    }

    @PostMapping
    public ServicoDTO salvar(@RequestBody ServicoDTO dto) {

        ServicoDTO s = service.salvar(dto);

        s.add(linkTo(methodOn(ServicoControle.class)
                .buscarPorId(s.getId())).withSelfRel());

        return s;
    }

    @PutMapping("/{id}")
    public ServicoDTO atualizar(@PathVariable Long id,
            @RequestBody ServicoDTO dto) {

        ServicoDTO s = service.atualizar(id, dto);

        s.add(linkTo(methodOn(ServicoControle.class)
                .buscarPorId(id)).withSelfRel());

        return s;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}