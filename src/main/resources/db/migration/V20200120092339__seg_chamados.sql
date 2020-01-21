CREATE TABLE seg_chamados(
    id                             BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    id_produto                                                BIGINT NOT NULL,
    titulo                                              VARCHAR(200) NOT NULL,
    descricao                                           VARCHAR(MAX) NOT NULL,
    status                                               VARCHAR(15) NOT NULL,
    data_hora_registro                                          DATE NOT NULL,
    CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES seg_produto(id)
);