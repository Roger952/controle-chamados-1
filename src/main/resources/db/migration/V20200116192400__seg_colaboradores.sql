CREATE TABLE seg_colaboradores
(

    id              BIGINT IDENTITY(1, 1)                   NOT NULL,
    nome            VARCHAR(100)                            NOT NULL,
    email           VARCHAR(100) UNIQUE                     NOT NULL,
    senha           VARCHAR(100)                            NOT NULL,
    id_empresa      BIGINT                                  NOT NULL,

    CONSTRAINT pk_id_colaborador          PRIMARY KEY (id),
    CONSTRAINT fk_colaborador_empresa     FOREIGN KEY (id_empresa) REFERENCES seg_empresa(id)
);