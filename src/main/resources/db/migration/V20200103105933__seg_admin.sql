 CREATE TABLE seg_admin(
    id             BIGINT IDENTITY(1, 1) NOT NULL,
    login          VARCHAR(100)         NOT NULL,
    senha          VARCHAR(100)         NOT NULL,
    CONSTRAINT pk_id_admin PRIMARY KEY (id)
);

