CREATE TABLE seg_atendente_produto
(
    id              BIGINT IDENTITY(1, 1) PRIMARY KEY    NOT NULL,
    id_atendente    BIGINT REFERENCES seg_atendentes(id) NOT NULL,
    id_produto      BIGINT REFERENCES seg_produto(id)    NOT NULL
);