CREATE TABLE seg_atendentes
(
    id BIGINT IDENTITY(1, 1)    NOT NULL PRIMARY KEY,
    nome  VARCHAR(100)          NOT NULL,
    foto  VARCHAR(50)           NOT NULL,
    email VARCHAR(50)           NOT NULL,
    senha VARCHAR(30)           NOT NULL
);