package br.com.hbsis.controlechamados.chamados;

import br.com.hbsis.controlechamados.chamados.arquivo.Arquivo;
import br.com.hbsis.controlechamados.produtos.Produto;
import java.time.LocalDateTime;
import java.util.List;

public class ChamadosDTO {

    private Long id;
    private List<Produto> produtoList;
    private String titulo;
    private String descricao;
    private List<Arquivo> multipartFileList;
    private String status;
    private LocalDateTime dataHoraRegistro;

    public ChamadosDTO() {
    }

    public ChamadosDTO(Long id, List<Produto> produtoList, String titulo, String descricao, List<Arquivo> multipartFileList, String status, LocalDateTime dataHoraRegistro) {
        this.id = id;
        this.produtoList = produtoList;
        this.titulo = titulo;
        this.descricao = descricao;
        this.multipartFileList = multipartFileList;
        this.status = status;
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public static ChamadosDTO of(Chamados chamados){
        return new ChamadosDTO(
                chamados.getId(),
                chamados.getProdutoList(),
                chamados.getTitulo(),
                chamados.getDescricao(),
                chamados.getMultipartFileList(),
                chamados.getStatus(),
                chamados.getDataHoraRegistro()
        );

    }

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
        return "ChamadosDTO{" +
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
