import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Empresa } from '../empresa';
import { Colaborador } from '../colaborador';
import { Produtos} from '../produtos'; 
import { ColaboradorService } from '../colaborador.service';
import { EmpresaService } from '../empresa.service';
import { ProdutosService } from '../produtos.service';

@Component({
  selector: 'app-colaborador',
  templateUrl: './colaborador.component.html',
  styleUrls: ['./colaborador.component.css']
})
export class ColaboradorComponent implements OnInit {

  colaborador: Colaborador = new Colaborador();
  submitted = false;

  /* LISTA DE PRODUTOS */
  produtos = new FormControl();
  produtoList: Produtos[];

  empresas = new FormControl();
  empresasList: Empresa[];

  /* FILE */
  selectedFiles: FileList;
  currentFileUpload: File;
  filename: string;

  /* RETORNO DE ERROS AO USER */
  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  constructor(private colaboradorService : ColaboradorService, private produtoService: ProdutosService, private empresaService: EmpresaService) { }

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

  }

  newAtendente(): void {
    this.submitted = false;
    this.colaborador = new Colaborador();
  }

  save() {
    
    if(this.confirmacaoSenha()){
      this.msgErro = 'As senhas não correspondem';
      this.erro = true;
      this.sucesso = false;
      console.log('Deu ruim!!!');

    } else {
    this.colaboradorService.createColaborador(this.colaborador).subscribe(
      (data) => {
      this.msgSucesso = 'Cadastro realizado com sucesso!';
      this.erro = false;
      this.sucesso = true;
      console.log(this.msgSucesso);
      this.limpar();
  },
    (error) => {
      this.msgErro = error.error[0].mensagemDesenvolvedor;
      this.erro = true;
      this.sucesso = false;
      console.log(this.msgErro);
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
    this.colaborador.empresaId = 1;

    (<HTMLInputElement>document.getElementById('senhaConfirmacao')).value = '';

  }

  confirmacaoSenha(): boolean{
     const senhaConfirmacao = (<HTMLInputElement>document.getElementById('senhaConfirmacao')).value;
    if(this.colaborador.senha != senhaConfirmacao){

      this.erro = true;
      this.sucesso = false;
      return this.erro;
    }
  }

  selectEmpresa(){
    
    console.log(this.colaborador.empresaId);
  }


  selectClickProduto(produtos) {

    const index = this.colaborador.produtoList.indexOf(produtos, 0);
    if(index > -1){
      this.colaborador.produtoList.splice(index, 1);
      this.colaborador.produtoList.push(produtos);
    }
    console.log(this.colaborador.produtoList);
  }
}