package br.com.hbsis.controlechamados.atendenteproduto;

public class AtendenteProdutoDTO {

    private Long id;
    private Long idAtendente;
    private Long idProduto;

    public AtendenteProdutoDTO() {
    }

    public AtendenteProdutoDTO(Long id, Long idAtendente, Long idProduto) {
        this.id = id;
        this.idAtendente = idAtendente;
        this.idProduto = idProduto;
    }

    public static AtendenteProdutoDTO of(AtendenteProduto atendenteProduto){
        return new AtendenteProdutoDTO(
          atendenteProduto.getId(),
          atendenteProduto.getAtendente().getId(),
          atendenteProduto.getProduto().getId()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAtendente() {
        return idAtendente;
    }

    public void setIdAtendente(Long idAtendente) {
        this.idAtendente = idAtendente;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public String toString() {
        return "AtendenteProdutoDTO{" +
                "id=" + id +
                ", idAtendente=" + idAtendente +
                ", idProduto=" + idProduto +
                '}';
    }
}
