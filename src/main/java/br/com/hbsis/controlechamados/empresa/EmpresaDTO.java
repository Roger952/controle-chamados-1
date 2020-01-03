package br.com.hbsis.controlechamados.empresa;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmpresaDTO {

    private Long Id;

    @NotNull
    @Size(min = 1, max = 50)
    private String razaoSocial;

    @NotNull
    @Size(min = 1, max = 50)
    private String nomeFantasia;

    @NotNull
    @Size(min = 14, max = 14)
    private String cnpj;

    @NotNull
    @Size(min = 1, max = 10)
    private Long ie;

    @NotNull
    @Email(message = "E-mail deve ser válido")
    @Size(min = 1, max = 50)
    private String email;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Long id, String razaoSocial, String nomeFantasia, String cnpj, Long ie, String email) {
        Id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.ie = ie;
        this.email = email;
    }

    public static EmpresaDTO of(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getId(),
                empresa.getCnpj(),
                empresa.getRazaoSocial(),
                empresa.getNomeFantasia(),
                empresa.getIe(),
                empresa.getEmail()
        );
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public Long getIe() {
        return ie;
    }

    public void setIe(Long ie) {
        this.ie = ie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
