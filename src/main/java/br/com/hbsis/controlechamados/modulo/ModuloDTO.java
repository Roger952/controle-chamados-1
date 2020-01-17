package br.com.hbsis.controlechamados.modulo;

public class ModuloDTO {

    private Long id, codigo;
    private String nomeModulo;
    private String nomeProduto;

    public ModuloDTO(Long id, Long codigo, String nomeModulo, String nomeProduto) {
        this.id = id;
        this.nomeModulo = nomeModulo;
        this.nomeProduto = nomeProduto;
        this.codigo = codigo;
    }

    public ModuloDTO() {
    }

    public static ModuloDTO of(Modulo modulo) {
        return new ModuloDTO(
                modulo.getId(),
                modulo.getCodigo(),
                modulo.getNomeModulo(),
                modulo.getProduto().getNome()
                );
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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
                ", codigo=" + codigo +
                ", nomeModulo='" + nomeModulo + '\'' +
                ", nomeProduto='" + nomeProduto + '\'' +
                '}';
    }
}
