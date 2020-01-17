package br.com.hbsis.controlechamados.empresa;

import br.com.hbsis.controlechamados.utils.cnpj.ValidationCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmpresaDTO {

    private Long id, codigo;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String ie;
    private String email;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Long id, Long codigo ,String razaoSocial, String nomeFantasia, String cnpj, String ie, String email) {
        this.id = id;
        this.codigo = codigo;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.ie = ie;
        this.email = email;
    }

    public static EmpresaDTO of(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getId(),
                empresa.getCodigo(),
                empresa.getCnpj(),
                empresa.getRazaoSocial(),
                empresa.getNomeFantasia(),
                empresa.getIe(),
                empresa.getEmail()
        );
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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
}
