package br.com.hbsis.controlechamados.admin;

import br.com.hbsis.controlechamados.permissao.Permissao;
import br.com.hbsis.controlechamados.utils.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "seg_admin")
public class Admin extends AbstractEntity {

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private EnumRoles roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "codigo_usuario")
            , inverseJoinColumns = @JoinColumn(name = "codigo_permissao"))
    private List<Permissao> permissoes;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public EnumRoles getRoles() {
        return roles;
    }

    public void setRoles(EnumRoles roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", permissoes=" + permissoes +
                '}';
    }
}