CREATE TABLE usuario_permissao
(
    codigo_usuario      BIGINT NOT NULL,
    codigo_permissao    BIGINT NOT NULL,

    PRIMARY KEY (codigo_usuario, codigo_permissao),

    CONSTRAINT fk_codigo_usuario     FOREIGN KEY (codigo_usuario)    REFERENCES seg_admin(id),
    CONSTRAINT fk_codigo_permissao   FOREIGN KEY (codigo_permissao)  REFERENCES seg_permissao(id)
);