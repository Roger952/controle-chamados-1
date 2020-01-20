CREATE TABLE seg_colaborador_produto
(
    id_colaborador BIGINT NOT NULL,
    id_produto     BIGINT NOT NULL,

    CONSTRAINT fk_id_colaborador    FOREIGN KEY (id_colaborador)    REFERENCES seg_colaboradores(id),
    CONSTRAINT fk_id_produto_colab  FOREIGN KEY (id_produto)        REFERENCES seg_produto(id)

);