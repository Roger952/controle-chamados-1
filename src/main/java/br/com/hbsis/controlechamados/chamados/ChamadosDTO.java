package br.com.hbsis.controlechamados.chamados;

import br.com.hbsis.controlechamados.chamados.arquivo.ArquivoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChamadosDTO {

    private Long id;
    private Long produtoId;
    private String titulo;
    private String descricao;
    private List<ArquivoDTO> arquivoDTOS;
    private String status;
    private Date dataHoraRegistro;

    public ChamadosDTO() {
    }

    public ChamadosDTO(Long id, Long produtoId, String titulo, String descricao, List<ArquivoDTO> arquivoDTOS, String status, Date dataHoraRegistro) {
        this.id = id;
        this.produtoId = produtoId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.arquivoDTOS = arquivoDTOS;
        this.status = status;
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public static ChamadosDTO of(Chamados chamados) {

        List<ArquivoDTO> arquivoDTOS = new ArrayList<ArquivoDTO>();

        chamados.getArquivoList().forEach(arquivo -> arquivoDTOS.add(ArquivoDTO.of(arquivo)));

        return new ChamadosDTO(
                chamados.getId(),
                chamados.getProduto().getId(),
                chamados.getTitulo(),
                chamados.getDescricao(),
                arquivoDTOS,
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

    public List<ArquivoDTO> getArquivoDTOS() {
        return arquivoDTOS;
    }

    public void setArquivoDTOS(List<ArquivoDTO> arquivoDTOS) {
        this.arquivoDTOS = arquivoDTOS;
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
        return "ChamadosDTO{" +
                "id=" + id +
                ", produto='" + produtoId + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", arquivoDTOS=" + arquivoDTOS +
                ", status='" + status + '\'' +
                ", dataHoraRegistro=" + dataHoraRegistro +
                '}';
    }
}
