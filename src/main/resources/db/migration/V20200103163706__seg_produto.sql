CREATE TABLE seg_produto
(
    id    INTEGER IDENTITY (1, 1) PRIMARY KEY,
    nome_produto VARCHAR(100)           NOT NULL
);

CREATE UNIQUE INDEX ix_seg_produto_login ON seg_produto (nome_produto ASC);