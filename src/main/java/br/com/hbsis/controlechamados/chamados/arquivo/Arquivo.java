package br.com.hbsis.controlechamados.chamados.arquivo;

import javax.persistence.*;

@Entity
@Table(name = "seg_arquivos")
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_arquivo", nullable = false, length = 200)
    private String nomeArquivo;


    @Column(name = "arquivo")
    private byte[] arquivo;

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
        return "Arquivo{" +
                "id=" + id +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", arquivo=" + arquivo +
                '}';
    }
}
