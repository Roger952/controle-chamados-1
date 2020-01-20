package br.com.hbsis.controlechamados.usuario.atendente;

import br.com.hbsis.controlechamados.atendenteproduto.AtendenteProduto;
import br.com.hbsis.controlechamados.produtos.Produto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "seg_atendentes")
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = false, nullable = false, length = 100)
    private String nome;

    @Column(name = "foto", unique = false, nullable = false, length = 50)
    private String foto;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "senha", unique = false, nullable = false, length = 30)
    private String senha;

    /** MUITOS ATENDENTES PARA MUITOS PRODUTOS */
    @ManyToMany
    @JoinTable(
            name = "seg_atendente_produto",
            joinColumns = @JoinColumn(name = "id_atendente"),
            inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Produto> produtoList;


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

    /** @ManyToMany */
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public String toString() {
        return "Atendente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", foto='" + foto + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", produtoList=" + produtoList +
                '}';
    }
}
