import { Produtos } from './produtos';

export class Chamado {
    id: number;
    titulo: string;
    descricao: string;
    nome_arquivo: string;
    produtoList: Produtos[];
}
