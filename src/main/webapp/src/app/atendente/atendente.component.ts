import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Atendente } from '../atendente';
import { AtendenteService } from '../atendente.service';
import { ProdutosService } from '../produtos.service';
import { Produtos } from '../produtos';

@Component({
  selector: 'app-atendente',
  templateUrl: './atendente.component.html',
  styleUrls: ['./atendente.component.css']
})
export class AtendenteComponent implements OnInit {

  atendente: Atendente = new Atendente();
  submitted = false;

  /* RETORNO DE ERROS AO USER */
  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  /* LIMPAR OS CAMPOS DE TEXTO */
  nome: string;
  email: string;
  senha: string;

  constructor(private atendenteService: AtendenteService, private produtoService: ProdutosService) { }

  toppings = new FormControl();
  toppingList: Produtos[];


  ngOnInit() {
    this.produtoService.getProdutosList().subscribe(data => {
      this.toppingList = data;
    }, error => {
      console.log(error)
    });
  }

  newAtendente(): void {
    this.submitted = false;
    this.atendente = new Atendente();
  }
  save() {
    this.atendenteService.createAtendente(this.atendente).subscribe(
        (data) => {
          this.msgSucesso = 'Cadastro realizado com sucesso!';
          this.erro = false;
          this.sucesso = true;
          this.limpar();
          console.log(this.msgSucesso);
      }, 
        (error) => {
          this.msgErro = error.error[0].mensagemDesenvolvedor;
          this.erro = true;
          this.sucesso = false;
          console.log(this.msgErro);
    });
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  /* LIMPAR OS CAMPOS APÓS CADASTRO */
  limpar() {

    this.nome = (<HTMLInputElement>document.getElementById("nome")).value;
    this.nome = "";

    this.email = (<HTMLInputElement>document.getElementById("email")).value;
    this.email = "";

    this.senha = (<HTMLInputElement>document.getElementById("senha")).value;
    this.senha = "";
  }
}
