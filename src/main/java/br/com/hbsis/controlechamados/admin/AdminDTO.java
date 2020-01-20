package br.com.hbsis.controlechamados.admin;

public class AdminDTO  {
    private Long id;
    private String login, senha;
    private EnumRoles role;

    public AdminDTO(Long id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public AdminDTO( String login, String senha, EnumRoles role) {
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    public AdminDTO() {

    }

    public static AdminDTO of(Admin admin){
        return new AdminDTO(admin.getId(), admin.getLogin(), admin.getSenha());
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

    public EnumRoles getRole() {
        return role;
    }

    public void setRole(EnumRoles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}