package br.com.hbsis.controlechamados.usuario.atendente;

import br.com.hbsis.controlechamados.produtos.Produto;

import java.util.ArrayList;
import java.util.List;

public class AtendenteDTO {

    /** ATRIBUTOS */
    private Long id;
    private String nome;
    private String foto;
    private String email;
    private List<Produto> produtoList;
    private String senha;

    /** CONSTRUTORES */
    public AtendenteDTO() {
    }

    public AtendenteDTO(Long id, String nome, String foto, String email, List<Produto> produtoList, String senha) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.email = email;
        this.produtoList = produtoList;
        this.senha = senha;
    }

    public static AtendenteDTO of(Atendente atendente) {

        List<Produto> produtoList = new ArrayList<>();
        for(int i=0; i>atendente.getProdutoList().size(); i++){
            produtoList.add(atendente.getProdutoList().get(i));
        }

        return new AtendenteDTO(
                atendente.getId(),
                atendente.getNome(),
                atendente.getFoto(),
                atendente.getEmail(),
                produtoList,
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /** @ManyToMany - List */
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public String toString() {
        return "AtendenteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", foto='" + foto + '\'' +
                ", email='" + email + '\'' +
                ", produtoList=" + produtoList +
                ", senha='" + senha + '\'' +
                '}';
    }
}
