package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.produtos.Produto;
import br.com.hbsis.controlechamados.utils.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "modulo")
public class Modulo extends AbstractEntity {

    @Column(name = "nome", length = 150, nullable = false)
    private String nomeModulo;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    public String getNomeModulo() {
        return nomeModulo;
    }

    public void setNomeModulo(String nomeModulo) {
        this.nomeModulo = nomeModulo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "nomeProduto='" + nomeModulo + '\'' +
                ", produto=" + produto +
                '}';
    }
}
