create table seg_empresa
(
id    BIGINT IDENTITY (1, 1) PRIMARY KEY NOT NULL,
razao_social VARCHAR(50)       NOT NULL,
nome_fantasia  VARCHAR(50)     NOT NULL,
cnpj VARCHAR(14)               NOT NULL,
ie  VARCHAR (10)               NOT NULL,
email VARCHAR(50)              NOT NULL
);
create unique index ix_seg_empresa_01 on seg_empresa (cnpj asc);
create unique index ix_seg_empresa_02 on seg_empresa (email asc);