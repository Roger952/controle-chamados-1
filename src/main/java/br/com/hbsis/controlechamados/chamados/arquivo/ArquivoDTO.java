package br.com.hbsis.controlechamados.chamados.arquivo;

public class ArquivoDTO {

    private Long id;
    private String nomeArquivo;
    private Long idChamados;
    private Byte arquivo;

    public ArquivoDTO() {
    }

    public ArquivoDTO(Long id, String nomeArquivo, Long idChamados, Byte arquivo) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.idChamados = idChamados;
        this.arquivo = arquivo;
    }

    public static ArquivoDTO of(Arquivo arquivo) {
        return new ArquivoDTO(
                arquivo.getId(),
                arquivo.getNomeArquivo(),
                arquivo.getIdChamados().getId(),
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

    public Long getIdChamados() {
        return idChamados;
    }

    public void setIdChamados(Long idChamados) {
        this.idChamados = idChamados;
    }

    public Byte getArquivo() {
        return arquivo;
    }

    public void setArquivo(Byte arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public String toString() {
        return "ArquivoDTO{" +
                "id=" + id +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", idChamados=" + idChamados +
                ", arquivo=" + arquivo +
                '}';
    }
}
