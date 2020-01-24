alter table seg_arquivos  ADD  id_chamado BIGINT;
alter table seg_arquivos  ADD CONSTRAINT fk_arquivo_chamados  FOREIGN KEY (id_chamado) REFERENCES seg_chamados(id);