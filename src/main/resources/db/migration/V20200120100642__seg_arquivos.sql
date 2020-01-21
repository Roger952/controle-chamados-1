CREATE TABLE seg_arquivos(
    id                               BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    nome_arquivo                                          VARCHAR(200) NOT NULL,
    arquivo                                               VARCHAR(MAX) NOT NULL
);

