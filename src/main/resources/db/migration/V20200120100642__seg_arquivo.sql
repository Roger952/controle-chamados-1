
CREATE TABLE seg_arquivos
(
    id BIGINT IDENTITY(1, 1)    NOT NULL PRIMARY KEY,
    nome_arquivo  VARCHAR(200)            NOT NULL,
    id_chamados BIGINT NOT NULL,
    arquivo  VARCHAR(MAX)            NOT NULL,
    constraint fk_chamados FOREIGN KEY (id_chamados) REFERENCES seg_chamados (id)
);

create unique index ix_id_arquivos on seg_arquivos(id);

