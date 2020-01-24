import { Produtos } from './produtos';

export class Colaborador {
    id: number;
    nome: string;
    email: string;
    senha: string;
    empresa: number;
    produtoList: Produtos[];
}
