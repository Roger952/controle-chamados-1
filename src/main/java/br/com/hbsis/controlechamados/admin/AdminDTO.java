package br.com.hbsis.controlechamados.admin;

import br.com.hbsis.controlechamados.permissao.Permissao;
import br.com.hbsis.controlechamados.produtos.Produto;

import java.util.ArrayList;
import java.util.List;

public class AdminDTO  {

    private Long id;
    private String login, senha;

    /* TESTE */
    private List<Permissao> permissaoList;

    public AdminDTO(Long id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public AdminDTO(Long id, String login, String senha, List<Permissao> permissaoList) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.permissaoList = permissaoList;
    }

    public static AdminDTO of(Admin admin){

        List<Permissao> permissaoList = new ArrayList<>();

        for(int i=0; i>admin.getPermissaoList().size(); i++){
            permissaoList.add(admin.getPermissaoList().get(i));
        }

        return new AdminDTO(
                admin.getId(),
                admin.getLogin(),
                admin.getSenha(),
                permissaoList
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    /* TESTE */
    public List<Permissao> getPermissaoList() {
        return permissaoList;
    }

    public void setPermissaoList(List<Permissao> permissaoList) {
        this.permissaoList = permissaoList;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", permissaoList=" + permissaoList +
                '}';
    }
}