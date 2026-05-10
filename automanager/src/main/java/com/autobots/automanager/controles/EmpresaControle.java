package com.autobots.automanager.controles;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.dto.EmpresaDTO;
import com.autobots.automanager.servicos.EmpresaServico;

@RestController
@RequestMapping("/empresas")
public class EmpresaControle {

        @Autowired
        private EmpresaServico service;

        @GetMapping
        public CollectionModel<EmpresaDTO> listar() {

                List<EmpresaDTO> empresas = service.listar().stream().map(e -> {

                        e.add(linkTo(methodOn(EmpresaControle.class)
                                        .buscarPorId(e.getId())).withSelfRel());

                        return e;

                }).collect(Collectors.toList());

                return CollectionModel.of(empresas,
                                linkTo(methodOn(EmpresaControle.class)
                                                .listar()).withSelfRel());
        }

        @GetMapping("/{id}")
        public EmpresaDTO buscarPorId(@PathVariable Long id) {

                EmpresaDTO e = service.buscarPorId(id);

                e.add(linkTo(methodOn(EmpresaControle.class)
                                .buscarPorId(id)).withSelfRel());

                e.add(linkTo(methodOn(EmpresaControle.class)
                                .listar()).withRel("empresas"));

                e.add(linkTo(methodOn(EmpresaControle.class)
                                .deletar(id)).withRel("deletar"));

                return e;
        }

        @PostMapping
        public EmpresaDTO salvar(@RequestBody EmpresaDTO dto) {

                EmpresaDTO e = service.salvar(dto);

                e.add(linkTo(methodOn(EmpresaControle.class)
                                .buscarPorId(e.getId())).withSelfRel());

                return e;
        }

        @PutMapping("/{id}")
        public EmpresaDTO atualizar(@PathVariable Long id,
                        @RequestBody EmpresaDTO dto) {

                EmpresaDTO e = service.atualizar(id, dto);

                e.add(linkTo(methodOn(EmpresaControle.class)
                                .buscarPorId(id)).withSelfRel());

                return e;
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {

                service.deletar(id);

                return ResponseEntity.noContent().build();
        }
}