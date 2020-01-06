CREATE TABLE seg_atendente_produto
(
    id              BIGINT IDENTITY(1, 1)                   NOT NULL PRIMARY KEY,
    id_atendente    BIGINT REFERENCES seg_atendentes(id)    NOT NULL,
    id_produto      INTEGER REFERENCES seg_produto(id)      NOT NULL
);