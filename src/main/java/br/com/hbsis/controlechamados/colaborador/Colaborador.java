package br.com.hbsis.controlechamados.colaborador;

import br.com.hbsis.controlechamados.empresa.Empresa;
import br.com.hbsis.controlechamados.produtos.Produto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "seg_colaboradores")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false,  length = 100)
    private String nome;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id", nullable = false)
    private Empresa empresa;

    @ManyToMany
    @JoinTable(name = "seg_colaborador_produto", joinColumns = @JoinColumn(name = "id_colaborador"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Produto> produtoList;

    public Colaborador() {
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
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
        return "Colaborador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", empresa=" + empresa +
                ", produtoList=" + produtoList +
                '}';
    }
}
