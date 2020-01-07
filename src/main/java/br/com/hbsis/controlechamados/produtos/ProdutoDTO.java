package br.com.hbsis.controlechamados.produtos;

public class ProdutoDTO {
    private Long id;
    private String nomeProduto;

    public ProdutoDTO(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public static ProdutoDTO of(Produto produto){
        return new ProdutoDTO(produto.getNomeProduto());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                '}';
    }
}