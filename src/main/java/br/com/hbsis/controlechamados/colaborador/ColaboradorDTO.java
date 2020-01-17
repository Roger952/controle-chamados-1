package br.com.hbsis.controlechamados.colaborador;

import br.com.hbsis.controlechamados.produtos.Produto;

import java.util.List;

public class ColaboradorDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Long empresa;
    private List<Produto> produtoList;

    public ColaboradorDTO(Long id, String nome, String email, String senha, Long id_empresa, List<Produto> produtoList) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.empresa = id_empresa;
        this.produtoList = produtoList;
    }

    public static ColaboradorDTO of (Colaborador colaborador){
        return new ColaboradorDTO(
                colaborador.getId(),
                colaborador.getNome(),
                colaborador.getEmail(),
                colaborador.getSenha(),
                colaborador.getEmpresa().getId(),
                colaborador.getProdutoList()
        );
    }

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

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public String toString() {
        return "ColaboradorDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", id_empresa=" + empresa +
                ", produtoList=" + produtoList +
                '}';
    }
}
