package br.com.hbsis.controlechamados.produtos;

import br.com.hbsis.controlechamados.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "seg_produto")
public class Produto extends AbstractEntity {

    @Column(name = "nome_produto", unique = true, nullable = false, length = 50)
    private String nomeProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nomeProduto='" + nomeProduto + '\'' +
                '}';
    }
}