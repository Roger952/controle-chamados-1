import { Produtos } from './produtos';

export class Chamado {
    id: number;
    titulo: string;
    descricao: string;
    arquivo: string;
    status: string;
    dataHoraRegistro: Date;
    produtoList: Produtos[];
}
