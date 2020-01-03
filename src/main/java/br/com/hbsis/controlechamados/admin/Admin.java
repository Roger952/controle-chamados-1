package br.com.hbsis.controlechamados.admin;

import br.com.hbsis.controlechamados.entity.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "seg_admin")
public class Admin extends AbstractEntity {

    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "senha", nullable = false)
    private String senha;

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

    @Override
    public String toString() {
        return "Admin{" +
                "login='"   + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}