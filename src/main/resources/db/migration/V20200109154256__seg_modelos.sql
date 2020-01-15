CREATE TABLE seg_modulo
(
    id              BIGINT IDENTITY(1, 1) PRIMARY KEY    NOT NULL,
    nome            VARCHAR(150)  NOT NULL,
    id_produto      BIGINT REFERENCES seg_produto(id)    NOT NULL
);
CREATE UNIQUE INDEX ix_seg_modelo_01 ON seg_modulo(id_produto, nome);
