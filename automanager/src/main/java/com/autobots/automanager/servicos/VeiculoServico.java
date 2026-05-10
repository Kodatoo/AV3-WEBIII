package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.VeiculoDTO;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.enumeracoes.TipoVeiculo;
import com.autobots.automanager.repositorios.UsuarioRepository;
import com.autobots.automanager.repositorios.VeiculoRepository;

@Service
public class VeiculoServico {

    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<VeiculoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public VeiculoDTO buscarPorId(Long id) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        return converterParaDTO(veiculo);
    }

    public VeiculoDTO salvar(VeiculoDTO dto) {
        Veiculo veiculo = converterParaEntidade(dto);
        return converterParaDTO(repository.save(veiculo));
    }

    public VeiculoDTO atualizar(Long id, VeiculoDTO dto) {

        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        veiculo.setPlaca(dto.getPlaca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setMarca(dto.getMarca());
        veiculo.setAno(dto.getAno());
        veiculo.setTipo(dto.getTipo()); // 🔥 AQUI ESTAVA FALTANDO

        Usuario usuario = usuarioRepository.findById(dto.getProprietarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        veiculo.setProprietario(usuario);

        return converterParaDTO(repository.save(veiculo));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private VeiculoDTO converterParaDTO(Veiculo veiculo) {

        VeiculoDTO dto = new VeiculoDTO(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getModelo(),
                veiculo.getMarca(),
                veiculo.getAno(),
                veiculo.getProprietario() != null ? veiculo.getProprietario().getId() : null,
                veiculo.getTipo());

        if (veiculo.getTipo() == TipoVeiculo.CIVIC_G10) {
            dto.setMensagemSecreta("CIVIC G10 detectado: veículo mais brabo que existe");
        }

        return dto;
    }

    private Veiculo converterParaEntidade(VeiculoDTO dto) {

        Veiculo veiculo = new Veiculo();

        veiculo.setId(dto.getId());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setMarca(dto.getMarca());
        veiculo.setAno(dto.getAno());
        veiculo.setTipo(dto.getTipo()); // 🔥 AQUI TAMBÉM

        if (dto.getProprietarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getProprietarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            veiculo.setProprietario(usuario);
        }

        return veiculo;
    }
}