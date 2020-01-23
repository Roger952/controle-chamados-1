CREATE TABLE seg_chamado_arquivo(
    id_chamado                                                 BIGINT NOT NULL,
    id_arquivo                                                 BIGINT NOT NULL,
    CONSTRAINT fk_chamado FOREIGN KEY (id_chamado) REFERENCES seg_chamados(id),
    CONSTRAINT fk_arquivo FOREIGN KEY (id_arquivo) REFERENCES seg_arquivos(id)
);
