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

import com.autobots.automanager.dto.VeiculoDTO;
import com.autobots.automanager.servicos.VeiculoServico;

@RestController
@RequestMapping("/veiculos")
public class VeiculoControle {

    @Autowired
    private VeiculoServico service;

    @GetMapping
    public CollectionModel<VeiculoDTO> listar() {

        List<VeiculoDTO> veiculos = service.listar().stream().map(v -> {

            v.add(linkTo(methodOn(VeiculoControle.class)
                    .buscarPorId(v.getId())).withSelfRel());

            return v;

        }).collect(Collectors.toList());

        return CollectionModel.of(veiculos,
                linkTo(methodOn(VeiculoControle.class)
                        .listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public VeiculoDTO buscarPorId(@PathVariable Long id) {

        VeiculoDTO v = service.buscarPorId(id);

        v.add(linkTo(methodOn(VeiculoControle.class)
                .buscarPorId(id)).withSelfRel());

        v.add(linkTo(methodOn(VeiculoControle.class)
                .listar()).withRel("veiculos"));

        return v;
    }

    @PostMapping
    public VeiculoDTO salvar(@RequestBody VeiculoDTO dto) {

        VeiculoDTO v = service.salvar(dto);

        v.add(linkTo(methodOn(VeiculoControle.class)
                .buscarPorId(v.getId())).withSelfRel());

        return v;
    }

    @PutMapping("/{id}")
    public VeiculoDTO atualizar(@PathVariable Long id,
            @RequestBody VeiculoDTO dto) {

        VeiculoDTO v = service.atualizar(id, dto);

        v.add(linkTo(methodOn(VeiculoControle.class)
                .buscarPorId(id)).withSelfRel());

        return v;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}