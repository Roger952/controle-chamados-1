import { Produtos } from './produtos';

export class Chamado {
    id: number;
    titulo: string;
    descricao: string;
    arquivo: string;
    produtoList: Produtos[];
}
