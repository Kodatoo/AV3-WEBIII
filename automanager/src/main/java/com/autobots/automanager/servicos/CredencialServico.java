package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.CredencialDTO;
import com.autobots.automanager.entidades.Credencial;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.repositorios.CredencialRepository;
import com.autobots.automanager.repositorios.UsuarioRepository;

@Service
public class CredencialServico {

    @Autowired
    private CredencialRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<CredencialDTO> listar() {

        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public CredencialDTO buscarPorId(Long id) {

        Credencial credencial = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credencial não encontrada"));

        return converterParaDTO(credencial);
    }

    public CredencialDTO salvar(CredencialDTO dto) {

        Credencial credencial = converterParaEntidade(dto);

        return converterParaDTO(repository.save(credencial));
    }

    public CredencialDTO atualizar(Long id, CredencialDTO dto) {

        Credencial credencial = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credencial não encontrada"));

        credencial.setLogin(dto.getLogin());
        credencial.setSenha(dto.getSenha());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        credencial.setUsuario(usuario);

        return converterParaDTO(repository.save(credencial));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private CredencialDTO converterParaDTO(Credencial credencial) {

        return new CredencialDTO(
                credencial.getId(),
                credencial.getLogin(),
                credencial.getSenha(),
                credencial.getUsuario().getId());
    }

    private Credencial converterParaEntidade(CredencialDTO dto) {

        Credencial credencial = new Credencial();

        credencial.setId(dto.getId());
        credencial.setLogin(dto.getLogin());
        credencial.setSenha(dto.getSenha());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        credencial.setUsuario(usuario);

        return credencial;
    }
}