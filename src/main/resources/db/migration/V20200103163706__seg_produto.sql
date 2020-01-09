CREATE TABLE seg_produto(
    id                   BIGINT IDENTITY (1, 1) PRIMARY KEY NOT NULL,
    nome                                       VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX ix_seg_produto_01 ON seg_produto(id, nome);