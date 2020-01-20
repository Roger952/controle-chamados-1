CREATE TABLE seg_atendente_produto
(
    id_atendente  BIGINT  NOT NULL,
    id_produto    BIGINT  NOT NULL,

    CONSTRAINT fk_id_atendente FOREIGN KEY   (id_atendente) REFERENCES seg_atendentes(id),
    CONSTRAINT fk_id_produto   FOREIGN KEY   (id_produto)   REFERENCES seg_produto(id)
);