CREATE TABLE seg_modulo
(
    id              BIGINT IDENTITY(1, 1)  NOT NULL,
    nome            VARCHAR(150)    NOT NULL,
    id_produto      BIGINT          NOT NULL,

    CONSTRAINT pk_id_modulo             PRIMARY KEY  (id),
    CONSTRAINT fk_id_produto_modulo     FOREIGN KEY  (id_produto)  REFERENCES seg_produto(id)
);

CREATE UNIQUE INDEX ix_seg_modelo_01 ON seg_modulo(id_produto, nome);
