CREATE TABLE seg_produto (

    id      BIGINT IDENTITY (1, 1)      NOT NULL,
    nome    VARCHAR(100)                NOT NULL,
    CONSTRAINT pk_id_produto PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_seg_produto_01 ON seg_produto(id, nome);