package br.com.hbsis.controlechamados.chamados;

import br.com.hbsis.controlechamados.chamados.arquivo.Arquivo;
import br.com.hbsis.controlechamados.produtos.Produto;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chamados")
public class Chamados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "chamado_produto", joinColumns = @JoinColumn(name = "id_chamados"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Produto> produtoList;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToMany
    @JoinTable(name = "chamado_arquivo", joinColumns = @JoinColumn(name = "id_chamado"), inverseJoinColumns = @JoinColumn(name = "id_arquivo"))
    private List<Arquivo> multipartFileList;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "data_hora_registro", nullable = false)
    private LocalDateTime dataHoraRegistro;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
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

    public List<Arquivo> getMultipartFileList() {
        return multipartFileList;
    }

    public void setMultipartFileList(List<Arquivo> multipartFileList) {
        this.multipartFileList = multipartFileList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(LocalDateTime dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    @Override
    public String toString() {
        return "Chamados{" +
                "id=" + id +
                ", produtoList=" + produtoList +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", multipartFileList=" + multipartFileList +
                ", status='" + status + '\'' +
                ", dataHoraRegistro=" + dataHoraRegistro +
                '}';
    }
}