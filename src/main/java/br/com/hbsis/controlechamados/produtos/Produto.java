package br.com.hbsis.controlechamados.produtos;

import br.com.hbsis.controlechamados.utils.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "seg_produto")
public class Produto extends AbstractEntity {

    @Column(name = "nome", unique = true, nullable = false, length = 50)
    private String nome;
    @Column(name = "codigo",  unique = true, nullable = false, length = 50)
    private Long codigo;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

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
                ", codigo=" + codigo +
                '}';
    }
}