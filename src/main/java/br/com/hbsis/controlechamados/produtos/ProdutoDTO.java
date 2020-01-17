package br.com.hbsis.controlechamados.produtos;

public class ProdutoDTO {

    private Long id, codigo;
    private String nome;

    public ProdutoDTO() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public ProdutoDTO(String nome, Long codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public static ProdutoDTO of(Produto produto){
        return new ProdutoDTO(
                produto.getNome(),
                produto.getCodigo());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id=" + id +
                ", codigo=" + codigo +
                ", nome='" + nome + '\'' +
                '}';
    }
}