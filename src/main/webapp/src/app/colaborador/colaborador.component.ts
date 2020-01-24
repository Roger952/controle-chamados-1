import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Empresa } from '../empresa';
import { Colaborador } from '../colaborador';
import { Produtos } from '../produtos';
import { ColaboradorService } from '../colaborador.service';
import { EmpresaService } from '../empresa.service';
import { ProdutosService } from '../produtos.service';

@Component({
  selector: 'app-colaborador',
  templateUrl: './colaborador.component.html',
  styleUrls: ['./colaborador.component.css']
})
export class ColaboradorComponent implements OnInit {

  colaboradores: Colaborador[];

  colaborador: Colaborador = new Colaborador();
  colaborador2: Colaborador = new Colaborador();
  submitted = false;

  produtos = new FormControl();
  produtoList: Produtos[];

  empresas = new FormControl();
  empresasList: Empresa[];

  empresa: Empresa = new Empresa();

  selectedFiles: FileList;
  currentFileUpload: File;
  filename: string;

  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  cadastro = true;
  alteracao = false;

  constructor(private colaboradorService: ColaboradorService,
    private produtoService: ProdutosService,
    private empresaService: EmpresaService) { }

  ngOnInit() {
    this.produtoService.getProdutosList().subscribe(
      data => {
        this.produtoList = data;
      }, error => {
        console.log(error)
      });

    this.empresaService.getEmpresaList().subscribe(
      data => {
        this.empresasList = data;
      }, error => {
        console.log(error)
      });

    this.colaboradorService.getColaboradorList().subscribe(data => { this.colaboradores = data; }, error => { console.log(error); });

  }

  newAtendente(): void {
    this.submitted = false;
    this.colaborador = new Colaborador();
  }

  save() {

    if (this.confirmacaoSenhaCadastro()) {
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
          this.colaboradorService.getColaboradorList().subscribe(data => { this.colaboradores = data; }, error => { console.log(error); });
        },
        (error) => {
          this.msgErro = error.error[0].mensagemDesenvolvedor;
          this.erro = true;
          this.sucesso = false;
        });
    }
  }

  searchColaboradores() {
    this.colaboradorService.getColaboradorFindBy(this.colaborador.nome).subscribe(data => {
      this.colaboradores = data;
    }, error => {
      console.log(error);
    });
  };

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  limpar() {
    this.colaborador.nome = '';
    this.colaborador.email = '';
    this.colaborador.senha = '';
    this.colaborador.produtoList = [];
    this.colaborador.empresa = null;

    (<HTMLInputElement>document.getElementById('senhaConfirmacao')).value = '';
  }

  limparAlter(){
      this.colaborador2.nome = '';
      this.colaborador2.email = '';
      this.colaborador2.senha = '';
      this.colaborador2.produtoList = [];
      this.colaborador2.empresa = null;
      (<HTMLInputElement>document.getElementById('senhaConfirmacaoAlter')).value = ''
  }

  confirmacaoSenhaCadastro(): boolean {
    const senhaConfirmacao = (<HTMLInputElement>document.getElementById('senhaConfirmacao')).value;

    if (this.colaborador.senha != senhaConfirmacao) {
      this.erro = true;
      this.sucesso = false;
      return this.erro;
    }
  }

  confirmacaoSenhaAlteracao(): boolean {
      const senhaConfirmacaoAlter = (<HTMLInputElement>document.getElementById('senhaConfirmacaoAlter')).value;

      if (this.colaborador2.senha != senhaConfirmacaoAlter) {
        this.erro = true;
        this.sucesso = false;
        return this.erro;
      }
    }

  selectEmpresa() {
    console.log(this.colaborador.empresa);
  }

  preencherCampos(colaborador: Colaborador) {

    this.cadastro = false;
    this.alteracao = true;

    this.colaboradorService.getColaborador(colaborador.id).subscribe(
      data => {

        this.colaborador2 = data;

        console.log(data.empresa);
        this.colaborador2.senha = '';

        for (let i = 0; i < this.produtoList.length; i++) {
          this.compararProdutos(this.produtoList[i], this.colaborador2.produtoList[i]);
        }

        this.empresaService.getEmpresa(data.empresa).subscribe(
        empresa => { this.empresa = empresa; console.log(empresa) }, error2 => { console.log("triste") });

        (<HTMLInputElement>document.getElementById('senhaConfirmacaoAlter')).value = data.senha;

      }, error => console.log(error));
  }

  compararProdutos(produto1: Produtos, produto2: Produtos) {
    return produto1 && produto2 && produto1.id == produto2.id;
  }

  cancelar() {
    this.cadastro = true;
    this.alteracao = false;
    this.erro = false;
    this.sucesso = false;
  }

  selectClickProduto(produtos) {

    const index = this.colaborador.produtoList.indexOf(produtos, 0);
    if (index > -1) {
      this.colaborador.produtoList.splice(index, 1);
      this.colaborador.produtoList.push(produtos);
    }
    console.log(this.colaborador.produtoList);
  }

  update(){

    if (this.confirmacaoSenhaAlteracao()) {
      this.msgErro = 'As senhas não correspondem';
      this.erro = true;
      this.sucesso = false;
    } else {

    this.colaboradorService.updateColaborador(this.colaborador2).subscribe(
      (data) => {
        this.msgSucesso = 'Alteração realizada com sucesso!';
        this.erro = false;
        this.sucesso = true;
        console.log(this.msgSucesso);
        this.colaboradorService.getColaboradorList().subscribe(data => { this.colaboradores = data; }, error => { console.log(error); });
        this.cadastro = true;
        this.alteracao = false;
        this.limparAlter();
      },
      (error) => {
        this.msgErro = error.error[0].mensagemDesenvolvedor;
        this.erro = true;
        this.sucesso = false;
      });
    }
  }

}
