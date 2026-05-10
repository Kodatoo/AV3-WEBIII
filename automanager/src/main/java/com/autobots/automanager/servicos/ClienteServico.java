package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.ClienteDTO;
import com.autobots.automanager.dto.DocumentoDTO;
import com.autobots.automanager.dto.EnderecoDTO;
import com.autobots.automanager.dto.TelefoneDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio repositorio;

    public List<ClienteDTO> listar() {
        return repositorio.findAll()
                .stream()
                .map(this::converterClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return converterClienteDTO(cliente);
    }

    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente cliente = toEntity(dto);
        return converterClienteDTO(repositorio.save(cliente));
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setNomeSocial(dto.getNomeSocial());
        cliente.setDataNascimento(dto.getDataNascimento());

        return converterClienteDTO(repositorio.save(cliente));
    }

    public void deletar(Long id) {
        Cliente cliente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        repositorio.delete(cliente);
    }

    private ClienteDTO converterClienteDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();

        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setNomeSocial(cliente.getNomeSocial());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setDataCadastro(cliente.getDataCadastro());

        if (cliente.getDocumentos() != null) {
            dto.setDocumentos(cliente.getDocumentos()
                    .stream()
                    .map(doc -> {
                        DocumentoDTO d = new DocumentoDTO();
                        d.setId(doc.getId());
                        d.setTipo(doc.getTipo());
                        d.setNumero(doc.getNumero());
                        return d;
                    }).collect(Collectors.toList()));
        }

        if (cliente.getTelefones() != null) {
            dto.setTelefones(cliente.getTelefones()
                    .stream()
                    .map(tel -> {
                        TelefoneDTO t = new TelefoneDTO();
                        t.setId(tel.getId());
                        t.setDdd(tel.getDdd());
                        t.setNumero(tel.getNumero());
                        return t;
                    }).collect(Collectors.toList()));
        }

        if (cliente.getEndereco() != null) {
            EnderecoDTO e = new EnderecoDTO();
            e.setId(cliente.getEndereco().getId());
            e.setCidade(cliente.getEndereco().getCidade());
            e.setEstado(cliente.getEndereco().getEstado());
            e.setRua(cliente.getEndereco().getRua());
            e.setNumero(cliente.getEndereco().getNumero());
            dto.setEndereco(e);
        }

        return dto;
    }

    private Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();

        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setNomeSocial(dto.getNomeSocial());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setDataCadastro(dto.getDataCadastro());

        if (dto.getEndereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setRua(dto.getEndereco().getRua());
            endereco.setNumero(dto.getEndereco().getNumero());
            endereco.setCidade(dto.getEndereco().getCidade());
            endereco.setEstado(dto.getEndereco().getEstado());
            endereco.setBairro(dto.getEndereco().getBairro());
            endereco.setCodigoPostal(dto.getEndereco().getCodigoPostal());
            endereco.setInformacoesAdicionais(dto.getEndereco().getInformacoesAdicionais());
            cliente.setEndereco(endereco);
        }

        if (dto.getTelefones() != null) {
            List<Telefone> telefones = dto.getTelefones()
                    .stream()
                    .map(t -> {
                        Telefone telefone = new Telefone();
                        telefone.setDdd(t.getDdd());
                        telefone.setNumero(t.getNumero());
                        return telefone;
                    }).collect(Collectors.toList());
            cliente.setTelefones(telefones);
        }

        if (dto.getDocumentos() != null) {
            List<Documento> documentos = dto.getDocumentos()
                    .stream()
                    .map(d -> {
                        Documento doc = new Documento();
                        doc.setTipo(d.getTipo());
                        doc.setNumero(d.getNumero());
                        doc.setCliente(cliente);
                        return doc;
                    }).collect(Collectors.toList());
            cliente.setDocumentos(documentos);
        }
        return cliente;
    }
}