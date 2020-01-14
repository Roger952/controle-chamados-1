package br.com.hbsis.controlechamados.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import br.com.hbsis.controlechamados.admin.Admin;
import br.com.hbsis.controlechamados.admin.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class AppUserDetailsService implements UserDetailsService {

        @Autowired
        private IAdminRepository iAdminRepository; //TODO: Remover repositorio publico

        @Override
        public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
            Optional<Admin> adminOptional = iAdminRepository.findByLogin(login);
            Admin admin = adminOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválidos!"));
            return new User(admin.getLogin(), admin.getSenha(), getPermissoes(admin));
        }

        private Collection<? extends GrantedAuthority> getPermissoes(Admin admin) {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            admin.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
            return authorities;
        }
    }