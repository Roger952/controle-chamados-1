package br.com.hbsis.controlechamados.chamados;


import br.com.hbsis.controlechamados.chamados.arquivo.Arquivo;
import br.com.hbsis.controlechamados.produtos.Produto;
import br.com.hbsis.controlechamados.utils.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "seg_chamados")
public class Chamados extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private Produto produto;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "idChamados")
    private List<Arquivo> arquivoList;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "data_hora_registro", unique = true, nullable = false, length = 50)
    private Date dataHoraRegistro;

    public List<Arquivo> getArquivoList() {
        return arquivoList;
    }

    public void setArquivoList(List<Arquivo> arquivoList) {
        this.arquivoList = arquivoList;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(Date dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    @Override
    public String toString() {
        return "Chamados{" +
                "produto=" + produto +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", arquivoList=" + arquivoList +
                ", status='" + status + '\'' +
                ", dataHoraRegistro=" + dataHoraRegistro +
                '}';
    }
}

