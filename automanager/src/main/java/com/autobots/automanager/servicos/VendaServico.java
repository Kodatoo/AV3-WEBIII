package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.VendaDTO;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.enumeracoes.TipoUsuario;
import com.autobots.automanager.repositorios.UsuarioRepository;
import com.autobots.automanager.repositorios.VeiculoRepository;
import com.autobots.automanager.repositorios.VendaRepository;

@Service
public class VendaServico {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<VendaDTO> listar() {

        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public VendaDTO buscarPorId(Long id) {

        Venda venda = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        return converterParaDTO(venda);
    }

    public VendaDTO salvar(VendaDTO dto) {

        Venda venda = converterParaEntidade(dto);

        return converterParaDTO(repository.save(venda));
    }

    public VendaDTO atualizar(Long id, VendaDTO dto) {

        Venda venda = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        venda.setData(dto.getData());
        venda.setTotal(dto.getTotal());

        Usuario cliente = usuarioRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (cliente.getTipoUsuario() != TipoUsuario.CLIENTE) {
            throw new RuntimeException("Usuário não é CLIENTE");
        }

        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        venda.setCliente(cliente);
        venda.setVeiculo(veiculo);

        return converterParaDTO(repository.save(venda));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private VendaDTO converterParaDTO(Venda venda) {

        return new VendaDTO(
                venda.getId(),
                venda.getData(),
                venda.getTotal(),
                venda.getCliente().getId(),
                venda.getVeiculo().getId());
    }

    private Venda converterParaEntidade(VendaDTO dto) {

        Venda venda = new Venda();

        venda.setId(dto.getId());
        venda.setData(dto.getData());
        venda.setTotal(dto.getTotal());

        Usuario cliente = usuarioRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (cliente.getTipoUsuario() != TipoUsuario.CLIENTE) {
            throw new RuntimeException("Usuário não é CLIENTE");
        }

        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        venda.setCliente(cliente);
        venda.setVeiculo(veiculo);

        return venda;
    }
}