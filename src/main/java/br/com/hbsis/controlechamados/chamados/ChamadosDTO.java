package br.com.hbsis.controlechamados.chamados;

import br.com.hbsis.controlechamados.chamados.arquivo.Arquivo;
import br.com.hbsis.controlechamados.chamados.arquivo.ArquivoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChamadosDTO {

    private Long id;
    private Long produtoId;
    private String titulo;
    private String descricao;
    private List<Arquivo> multipartFileList;
    private String status;
    private LocalDateTime dataHoraRegistro;

    public ChamadosDTO() {
    }

    public ChamadosDTO(Long id, Long produtoId, String titulo, String descricao, List<Arquivo> multipartFileList, String status, LocalDateTime dataHoraRegistro) {
        this.id = id;
        this.produtoId = produtoId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.multipartFileList = multipartFileList;
        this.status = status;
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public static ChamadosDTO of(Chamados chamados){
        return new ChamadosDTO(
                chamados.getId(),
                chamados.getProduto().getId(),
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

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
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
                ", produtoId=" + produtoId +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", multipartFileList=" + multipartFileList +
                ", status='" + status + '\'' +
                ", dataHoraRegistro=" + dataHoraRegistro +
                '}';
    }
}
