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

import com.autobots.automanager.dto.ClienteDTO;
import com.autobots.automanager.servicos.ClienteServico;

@RestController
@RequestMapping("/clientes")
public class ClienteControle {

    @Autowired
    private ClienteServico service;

    @GetMapping
    public CollectionModel<ClienteDTO> listar() {
        List<ClienteDTO> clientes = service.listar();

        clientes = clientes.stream().map(cliente -> {
            cliente.add(linkTo(methodOn(ClienteControle.class)
                    .buscarPorId(cliente.getId())).withSelfRel());

            cliente.add(linkTo(methodOn(ClienteControle.class)
                    .deletar(cliente.getId())).withRel("deletar"));

            return cliente;
        }).collect(Collectors.toList());

        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteControle.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable Long id) {
        ClienteDTO cliente = service.buscarPorId(id);

        cliente.add(linkTo(methodOn(ClienteControle.class)
                .buscarPorId(id)).withSelfRel());

        cliente.add(linkTo(methodOn(ClienteControle.class)
                .listar()).withRel("clientes"));

        cliente.add(linkTo(methodOn(ClienteControle.class)
                .deletar(id)).withRel("deletar"));

        return cliente;
    }

    @PostMapping
    public ClienteDTO cadastrar(@RequestBody ClienteDTO dto) {
        ClienteDTO cliente = service.salvar(dto);

        cliente.add(linkTo(methodOn(ClienteControle.class)
                .buscarPorId(cliente.getId())).withSelfRel());

        return cliente;
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        ClienteDTO cliente = service.atualizar(id, dto);

        cliente.add(linkTo(methodOn(ClienteControle.class)
                .buscarPorId(id)).withSelfRel());

        return cliente;
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}