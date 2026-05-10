package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.UsuarioDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.repositorios.EmpresaRepository;
import com.autobots.automanager.repositorios.UsuarioRepository;
import com.autobots.automanager.enumeracoes.TipoUsuario;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<UsuarioDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return converterParaDTO(usuario);
    }

    public UsuarioDTO salvar(UsuarioDTO dto) {

        Usuario usuario = converterParaEntidade(dto);

        return converterParaDTO(repository.save(usuario));
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setTipoUsuario(dto.getTipoUsuario());

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        usuario.setEmpresa(empresa);

        return converterParaDTO(repository.save(usuario));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private UsuarioDTO converterParaDTO(Usuario usuario) {

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getTipoUsuario(),
                usuario.getEmpresa() != null ? usuario.getEmpresa().getId() : null);
    }

    private Usuario converterParaEntidade(UsuarioDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setTipoUsuario(dto.getTipoUsuario());

        if (dto.getEmpresaId() != null) {

            Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                    .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

            usuario.setEmpresa(empresa);
        }

        return usuario;
    }
}