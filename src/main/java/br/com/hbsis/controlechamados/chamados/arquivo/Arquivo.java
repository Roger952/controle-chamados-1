package br.com.hbsis.controlechamados.chamados.arquivo;

import br.com.hbsis.controlechamados.chamados.Chamados;

import javax.persistence.*;

@Entity
@Table(name = "seg_arquivos")
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_arquivo", nullable = false, length = 200)
    private String nomeArquivo;

    @ManyToOne
    @JoinColumn(name = "id_chamados", referencedColumnName = "id")
    private Chamados idChamados;

    @Column(name = "arquivo")
    private Byte arquivo;

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

    public Chamados getIdChamados() {
        return idChamados;
    }

    public void setIdChamados(Chamados idChamados) {
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
        return "Arquivo{" +
                "id=" + id +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", idChamados=" + idChamados +
                ", arquivo=" + arquivo +
                '}';
    }
}
