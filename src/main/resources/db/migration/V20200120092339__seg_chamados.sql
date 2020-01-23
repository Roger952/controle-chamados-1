CREATE TABLE seg_chamados
(
    id BIGINT IDENTITY(1, 1)    NOT NULL PRIMARY KEY,
    titulo  VARCHAR(200)            NOT NULL,
    descricao  VARCHAR(MAX)         NOT NULL,
    status VARCHAR(50)              NOT NULL,
    data_hora_registro DATETIME     NOT NULL
);

create unique index ix_id_chamados on seg_chamados(id);

