package br.com.hbsis.controlechamados.empresa;

import javax.persistence.*;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razao_social", unique = true, nullable = false, length = 50)
    private String razaoSocial;

    @Column(name = "nome_fantasia", unique = true, nullable = false, length = 50)
    private String nomeFantasia;

    @Column(name = "cnpj",  unique = true, nullable = false, length = 14)
    private String cnpj;

    @Column(name = "ie",  unique = true, nullable = false, length = 10)
    private String ie;

    @Column(name = "email",  unique = true, nullable = false, length = 50)
    private String email;

    public Empresa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "Id=" + id +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", ie=" + ie +
                ", email='" + email + '\'' +
                '}';
    }
}
