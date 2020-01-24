package br.com.hbsis.controlechamados.chamados.arquivo;

public class ArquivoDTO {

    private Long id;
    private String nomeArquivo;
    private byte[] arquivo;

    public ArquivoDTO() {
    }

    public ArquivoDTO(Long id, String nomeArquivo, byte[] arquivo) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.arquivo = arquivo;
    }

    public static ArquivoDTO of(Arquivo arquivo) {
        return new ArquivoDTO(
                arquivo.getId(),
                arquivo.getNomeArquivo(),
                arquivo.getArquivo()
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

    @Override
    public String toString() {
        return "ArquivoDTO{" +
                "id=" + id +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", arquivo=" + arquivo +
                '}';
    }
}