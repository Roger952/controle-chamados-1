package br.com.hbsis.controlechamados.security;

import br.com.hbsis.controlechamados.admin.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistema extends User {

    private static final long serialVersionUID = 1L;

    private Admin admin;

    public UsuarioSistema(Admin admin, Collection<? extends GrantedAuthority> authorities) {
        super(admin.getLogin(), admin.getSenha(), authorities);
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

}