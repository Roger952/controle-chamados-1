import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Empresa} from '../empresa';
import {Colaborador} from '../colaborador';
import {Produtos} from '../produtos';
import {ColaboradorService} from '../colaborador.service';
import {EmpresaService} from '../empresa.service';
import {ProdutosService} from '../produtos.service';

@Component({
  selector: 'app-colaborador',
  templateUrl: './colaborador.component.html',
  styleUrls: ['./colaborador.component.css']
})
export class ColaboradorComponent implements OnInit {
  /* LISTAR E EDITAR COLABORADORES */
  colaboradores: Colaborador[];

  produtoControl = new FormControl();
  produtosSelecionados: Produtos[];
  produtoList: Produtos[];

  colaborador: Colaborador = new Colaborador();
  submitted = false;

  id: number;

  empresaControl = new FormControl();
  empresasList: Empresa[];

  /* RETORNO DE ERROS AO USER */
  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  constructor(private colaboradorService: ColaboradorService,
              private produtoService: ProdutosService,
              private empresaService: EmpresaService) {
  }

  ngOnInit() {
    console.log("passeio");

    this.produtoService.getProdutosList().subscribe(
      data => {
        this.produtoList = data;
        // this.produtosSelecionados = data;
        // this.produtosSelecionados.push(data[2]);
      }, error => {
        console.log(error)
      });

    this.empresaService.getEmpresaList().subscribe(
      data => {
        this.empresasList = data;
      }, error => {
        console.log(error)
      });

    this.colaboradorService.getColaboradorList().subscribe(data => {
      this.colaboradores = data;
    }, error => {
      console.log(error);
    });

  }

  save() {

    if (this.confirmacaoSenha()) {
      this.msgErro = 'As senhas não correspondem';
      this.erro = true;
      this.sucesso = false;

    } else {
      this.colaboradorService.createColaborador(this.colaborador).subscribe(
        (data) => {
          this.msgSucesso = 'Cadastro realizado com sucesso!';
          this.erro = false;
          this.sucesso = true;
          console.log(this.msgSucesso);
          this.limpar();
          this.colaboradorService.getColaboradorList().subscribe(data => {
            this.colaboradores = data;
          }, error => {
            console.log(error);
          });

        },
        (error) => {
          this.msgErro = error.error[0].mensagemDesenvolvedor;
          this.erro = true;
          this.sucesso = false;
        });
    }
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  /* LIMPAR OS CAMPOS APÓS CADASTRO */
  limpar() {
    this.colaborador.nome = '';
    this.colaborador.email = '';
    this.colaborador.senha = '';
    this.colaborador.produtoList = [];
    this.colaborador.empresaId = null;

    (<HTMLInputElement>document.getElementById('senhaConfirmacao')).value = '';

  }

  confirmacaoSenha(): boolean {
    const senhaConfirmacao = (<HTMLInputElement>document.getElementById('senhaConfirmacao')).value;
    if (this.colaborador.senha != senhaConfirmacao) {

      this.erro = true;
      this.sucesso = false;
      return this.erro;
    }
  }

  selectEmpresa() {
    console.log(this.colaborador.empresaId);
  }

  selectClickProduto(produto) {

    console.log("produto selecionado....");
    console.log(produto);

    const index = this.colaborador.produtoList.indexOf(produto.data, 0);
    if (index > -1) {
      this.colaborador.produtoList.splice(index, 1);
      this.colaborador.produtoList.push(produto.data);
    }
    console.log(this.colaborador.produtoList);
  }

  searchColaboradores() {

    this.colaboradorService.getColaboradorFindBy(this.colaborador.nome).subscribe(data => {
      this.colaboradores = data;
    }, error => {
      console.log(error);
    });
  };

  listarUpdate(colaboradorObj: Colaborador) {

    console.log("Colaborador: " + colaboradorObj.id);

    this.colaboradorService.getColaborador(colaboradorObj.id).subscribe(data => {

        this.colaborador = data;

        for(let i = 0; i < this.colaborador.produtoList.length; i++){
          this.compararProdutos(this.produtoList[i], this.colaborador.produtoList[i]);
        }

        (<HTMLInputElement>document.getElementById('senhaConfirmacao')).value = data.senha;

      },
      error => console.log(error));
  }

  compararProdutos(produto1: Produtos, produto2: Produtos){
    return produto1 && produto2 && produto1.nome == produto2.nome;
  }


}
