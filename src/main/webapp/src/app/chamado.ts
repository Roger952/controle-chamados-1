import { Produtos } from './produtos';

export class Chamado {
    id: number;
    titulo: string;
    descricao: string;
    arquivoDTOS: string;
    status: string;
    dataHoraRegistro: Date;
    produtoList: Produtos[];
}
