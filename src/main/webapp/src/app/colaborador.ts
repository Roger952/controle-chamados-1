import { Produtos } from './produtos';
import { Empresa } from './empresa';

export class Colaborador {
    id: number;
    nome: string;
    email: string;
    senha: string;
    empresaList: Empresa[];
    produtoList: Produtos[];
}
