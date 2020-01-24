package br.com.hbsis.controlechamados.chamados.arquivo;

import java.util.Arrays;

public class ArquivoDTO {

    private Long id;
    private String nomeArquivo;
    private byte[] arquivo;
    private Long chamado;

    public ArquivoDTO() {
    }

    public ArquivoDTO(Long id, String nomeArquivo, byte[] arquivo, Long chamado) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.arquivo = arquivo;
        this.chamado = chamado;
    }

    public static ArquivoDTO of(Arquivo arquivo) {
        return new ArquivoDTO(
                arquivo.getId(),
                arquivo.getNomeArquivo(),
                arquivo.getArquivo(),
                arquivo.getChamados().getId()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Long getChamado() {
        return chamado;
    }

    public void setChamado(Long chamado) {
        this.chamado = chamado;
    }

    @Override
    public String toString() {
        return "ArquivoDTO{" +
                "id=" + id +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", arquivo=" + Arrays.toString(arquivo) +
                ", chamado=" + chamado +
                '}';
    }
}