exec sp_rename 'seg_chamados',               'chamados';
exec sp_rename 'seg_arquivos',               'arquivos';

exec sp_rename 'seg_chamados_produto',       'chamado_produto';
exec sp_rename 'seg_chamado_arquivo',        'chamado_arquivo';

