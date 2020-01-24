import { Component, OnInit } from '@angular/core';
import { Produtos } from '../produtos';
import { ProdutosService } from '../produtos.service';


@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  produto: Produtos = new Produtos;
  submitted = false;

  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  constructor(private produtoService: ProdutosService) { }

  ngOnInit() {
  }

  newProduto(): void {
    this.submitted = false;
    this.produto = new Produtos();
  }

  save(){
    this.produtoService.createProduto(this.produto).subscribe(
      (data) => {
        this.msgSucesso = 'Cadastro realizado com sucesso!';
        this.erro = false;
        this.sucesso = true;
        this.limpar();
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

  limpar() {
    this.produto.nome = '';
  }
}