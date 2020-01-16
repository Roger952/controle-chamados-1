import { Produtos } from './produtos';

export class Atendente {
    id: number;
    nome: string;
    email: string;
    senha: string;
    foto: string;
    produtoList: Produtos[];
}
