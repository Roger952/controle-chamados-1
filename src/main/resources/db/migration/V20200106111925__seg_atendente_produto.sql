CREATE TABLE seg_atendente_produto
(
    id_atendente   BIGINT REFERENCES seg_atendentes(id) NOT NULL,
    id_produto     BIGINT REFERENCES seg_produto(id)    NOT NULL
);