package br.com.hbsis.controlechamados.modulo;

public class ModuloDTO {

    private Long id;
    private String nomeModulo;
    private Long idProduto;

    public ModuloDTO(Long id, String nomeModulo, Long idProduto) {
        this.id = id;
        this.nomeModulo = nomeModulo;
        this.idProduto = idProduto;
    }

    public ModuloDTO() {
    }

    public static ModuloDTO of(Modulo modulo) {
        return new ModuloDTO(modulo.getId(), modulo.getNomeModulo(), modulo.getProduto().getId());
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

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public String toString() {
        return "ModuloDTO{" +
                "id=" + id +
                ", nomeModulo='" + nomeModulo + '\'' +
                ", idProduto=" + idProduto +
                '}';
    }
}
