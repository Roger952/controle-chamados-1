package br.com.hbsis.controlechamados.empresa;

import br.com.hbsis.controlechamados.utils.ValidationCNPJ;

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
    @ValidationCNPJ(length = 14, message = "CNPJ inválido")
    private String cnpj;

    @NotNull
    @Size(min = 1, max = 10)
    private String ie;

    @NotNull
    @Email(message = "E-mail deve ser válido")
    @Size(min = 1, max = 50)
    private String email;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Long id, String razaoSocial, String nomeFantasia, String cnpj, String ie, String email) {
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
}
