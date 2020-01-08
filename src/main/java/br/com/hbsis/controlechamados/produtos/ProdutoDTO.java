package br.com.hbsis.controlechamados.produtos;

public class ProdutoDTO {
    private Long id;
    private String nome;

    public ProdutoDTO() {
    }

    public ProdutoDTO(String nome) {
        this.nome = nome;
    }

    public static ProdutoDTO of(Produto produto){
        return new ProdutoDTO(produto.getNome());
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
                ", nomeProduto='" + nome + '\'' +
                '}';
    }
}