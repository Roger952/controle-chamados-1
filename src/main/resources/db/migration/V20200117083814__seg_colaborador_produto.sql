CREATE TABLE seg_colaborador_produto(
    id_colaborador BIGINT REFERENCES seg_colaboradores(id) NOT NULL,
    id_produto     BIGINT REFERENCES seg_produto(id)       NOT NULL
);