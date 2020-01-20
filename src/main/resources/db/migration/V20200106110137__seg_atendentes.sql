CREATE TABLE seg_atendentes
(
    id BIGINT IDENTITY(1, 1)    NOT NULL,
    nome  VARCHAR(100)          NOT NULL,
    foto  VARCHAR(50)           NOT NULL,
    email VARCHAR(50)           NOT NULL,
    senha VARCHAR(30)          NOT NULL,
    CONSTRAINT pk_id_atendente PRIMARY KEY (id)
);

create unique index ix_seg_atendentes_01 on seg_atendentes (email asc);