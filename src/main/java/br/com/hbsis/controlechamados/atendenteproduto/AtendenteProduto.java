package br.com.hbsis.controlechamados.atendenteproduto;

import br.com.hbsis.controlechamados.produtos.Produto;
import br.com.hbsis.controlechamados.usuario.atendente.Atendente;

import javax.persistence.*;

@Entity
@Table(name = "seg_atendente_produto")
public class AtendenteProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_atendente", referencedColumnName = "id")
    private Atendente atendente;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "AtendenteProduto{" +
                "id=" + id +
                ", atendente=" + atendente +
                ", produto=" + produto +
                '}';
    }
}