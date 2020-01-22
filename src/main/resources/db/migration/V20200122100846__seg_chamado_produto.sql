CREATE TABLE seg_chamados_produto
(
    id_chamados BIGINT NOT NULL,
    id_produto     BIGINT NOT NULL,

    CONSTRAINT fk_id_chamados    FOREIGN KEY (id_chamados)    REFERENCES seg_chamados(id),
    CONSTRAINT fk_id_produto_    FOREIGN KEY (id_produto)        REFERENCES seg_produto(id)

);