CREATE TABLE seg_colaboradores(
    id  BIGINT IDENTITY (1, 1)  PRIMARY KEY NOT NULL,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(30),
    id_empresa BIGINT FOREIGN KEY REFERENCES seg_empresa(id),
    id_produto BIGINT FOREIGN KEY REFERENCES seg_produto(id),
)

CREATE UNIQUE INDEX ix_seg_colaboradores ON seg_colaboradores(email asc);
