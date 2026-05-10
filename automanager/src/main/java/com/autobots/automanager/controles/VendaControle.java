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

import com.autobots.automanager.dto.VendaDTO;
import com.autobots.automanager.servicos.VendaServico;

@RestController
@RequestMapping("/vendas")
public class VendaControle {

    @Autowired
    private VendaServico service;

    @GetMapping
    public CollectionModel<VendaDTO> listar() {

        List<VendaDTO> vendas = service.listar().stream().map(v -> {

            v.add(linkTo(methodOn(VendaControle.class)
                    .buscarPorId(v.getId())).withSelfRel());

            return v;

        }).collect(Collectors.toList());

        return CollectionModel.of(vendas,
                linkTo(methodOn(VendaControle.class)
                        .listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public VendaDTO buscarPorId(@PathVariable Long id) {

        VendaDTO v = service.buscarPorId(id);

        v.add(linkTo(methodOn(VendaControle.class)
                .buscarPorId(id)).withSelfRel());

        v.add(linkTo(methodOn(VendaControle.class)
                .listar()).withRel("vendas"));

        return v;
    }

    @PostMapping
    public VendaDTO salvar(@RequestBody VendaDTO dto) {

        VendaDTO v = service.salvar(dto);

        v.add(linkTo(methodOn(VendaControle.class)
                .buscarPorId(v.getId())).withSelfRel());

        return v;
    }

    @PutMapping("/{id}")
    public VendaDTO atualizar(@PathVariable Long id,
            @RequestBody VendaDTO dto) {

        VendaDTO v = service.atualizar(id, dto);

        v.add(linkTo(methodOn(VendaControle.class)
                .buscarPorId(id)).withSelfRel());

        return v;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}