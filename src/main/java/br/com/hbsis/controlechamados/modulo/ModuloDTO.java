package br.com.hbsis.controlechamados.modulo;

public class ModuloDTO {

    private Long id;
    private String nomeModulo;
    private String nomeProduto;

    public ModuloDTO(Long id, String nomeModulo, String nomeProduto) {
        this.id = id;
        this.nomeModulo = nomeModulo;
        this.nomeProduto = nomeProduto;
    }

    public ModuloDTO() {
    }

    public static ModuloDTO of(Modulo modulo) {
        return new ModuloDTO(modulo.getId(), modulo.getNomeModulo(), modulo.getProduto().getNome());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeModulo() {
        return nomeModulo;
    }

    public void setNomeModulo(String nomeModulo) {
        this.nomeModulo = nomeModulo;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Override
    public String toString() {
        return "ModuloDTO{" +
                "id=" + id +
                ", nomeModulo='" + nomeModulo + '\'' +
                ", idProduto=" + nomeProduto +
                '}';
    }
}
