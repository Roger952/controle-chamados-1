import { Produtos } from './produtos';

export class Chamado {
    id: number;
    titulo: string;
    descricao: string;
    arquivos : FileList;
    status: string;
    dataHoraRegistro: Date;
    produtoList: Produtos[];
}
