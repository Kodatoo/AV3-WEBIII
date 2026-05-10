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

import com.autobots.automanager.dto.MercadoriaDTO;
import com.autobots.automanager.servicos.MercadoriaServico;

@RestController
@RequestMapping("/mercadorias")
public class MercadoriaControle {

    @Autowired
    private MercadoriaServico service;

    @GetMapping
    public CollectionModel<MercadoriaDTO> listar() {

        List<MercadoriaDTO> mercadorias = service.listar().stream().map(m -> {

            m.add(linkTo(methodOn(MercadoriaControle.class)
                    .buscarPorId(m.getId())).withSelfRel());

            return m;

        }).collect(Collectors.toList());

        return CollectionModel.of(mercadorias,
                linkTo(methodOn(MercadoriaControle.class)
                        .listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public MercadoriaDTO buscarPorId(@PathVariable Long id) {

        MercadoriaDTO m = service.buscarPorId(id);

        m.add(linkTo(methodOn(MercadoriaControle.class)
                .buscarPorId(id)).withSelfRel());

        m.add(linkTo(methodOn(MercadoriaControle.class)
                .listar()).withRel("mercadorias"));

        return m;
    }

    @PostMapping
    public MercadoriaDTO salvar(@RequestBody MercadoriaDTO dto) {

        MercadoriaDTO m = service.salvar(dto);

        m.add(linkTo(methodOn(MercadoriaControle.class)
                .buscarPorId(m.getId())).withSelfRel());

        return m;
    }

    @PutMapping("/{id}")
    public MercadoriaDTO atualizar(@PathVariable Long id,
            @RequestBody MercadoriaDTO dto) {

        MercadoriaDTO m = service.atualizar(id, dto);

        m.add(linkTo(methodOn(MercadoriaControle.class)
                .buscarPorId(id)).withSelfRel());

        return m;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}