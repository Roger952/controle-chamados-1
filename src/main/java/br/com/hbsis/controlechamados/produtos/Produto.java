package br.com.hbsis.controlechamados.produtos;

import br.com.hbsis.controlechamados.utils.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "produto")
public class Produto extends AbstractEntity {

    @Column(name = "nome", unique = true, nullable = false, length = 50)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                '}';
    }
}