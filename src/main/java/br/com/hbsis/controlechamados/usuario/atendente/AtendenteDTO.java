package br.com.hbsis.controlechamados.usuario.atendente;

import br.com.hbsis.controlechamados.atendenteproduto.AtendenteProduto;
import br.com.hbsis.controlechamados.atendenteproduto.AtendenteProdutoDTO;

import java.util.ArrayList;
import java.util.List;

public class AtendenteDTO {

    /** ATRIBUTOS */
    private Long id;
    private String nome;
    private String foto;
    private String email;
    private List<AtendenteProdutoDTO> atendenteProdutoDTOList;
    private String senha;

    /** CONSTRUTORES */
    public AtendenteDTO() {
    }

    public AtendenteDTO(Long id, String nome, String foto, String email, List<AtendenteProdutoDTO> atendenteProdutoDTOList, String senha) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.email = email;
        this.atendenteProdutoDTOList = atendenteProdutoDTOList;
        this.senha = senha;
    }

    public static AtendenteDTO of(Atendente atendente) {

        List<AtendenteProdutoDTO> atendenteProdutoDTOList = new ArrayList<>();
        atendente.getAtendenteProdutoList().forEach(atendenteProduto -> atendenteProdutoDTOList.add(AtendenteProdutoDTO.of(atendenteProduto)));

        return new AtendenteDTO(
                atendente.getId(),
                atendente.getNome(),
                atendente.getFoto(),
                atendente.getEmail(),
                atendenteProdutoDTOList,
                atendente.getSenha()
        );
    }

    /** GETTER & SETTER */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AtendenteProdutoDTO> getAtendenteProdutoDTOList() {
        return atendenteProdutoDTOList;
    }

    public void setAtendenteProdutoDTOList(List<AtendenteProdutoDTO> atendenteProdutoDTOList) {
        this.atendenteProdutoDTOList = atendenteProdutoDTOList;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "AtendenteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", foto='" + foto + '\'' +
                ", email='" + email + '\'' +
                ", atendenteProdutoDTOList=" + atendenteProdutoDTOList +
                ", senha='" + senha + '\'' +
                '}';
    }
}
