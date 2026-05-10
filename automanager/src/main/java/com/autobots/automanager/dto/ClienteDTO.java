package com.autobots.automanager.dto;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

public class ClienteDTO extends RepresentationModel<ClienteDTO> {

    private Long id;
    private String nome;
    private String nomeSocial;
    private Date dataNascimento;
    private Date dataCadastro;

    private List<DocumentoDTO> documentos;
    private EnderecoDTO endereco;
    private List<TelefoneDTO> telefones;

    public ClienteDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public List<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setDocumentos(List<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public void setTelefones(List<TelefoneDTO> telefones) {
        this.telefones = telefones;
    }
}