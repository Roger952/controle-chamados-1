import { Component, OnInit } from '@angular/core';
import { Produtos } from '../produtos';
import { ProdutosService } from '../produtos.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  produto: Produtos = new Produtos;
  submitted = false;

  constructor(private produtoService: ProdutosService, private router: Router) { }

  ngOnInit() {
  }

  newProduto(): void {
    this.submitted = false;
    this.produto = new Produtos();
  }

  onSubmit() {
    this.submitted = true;

    
    if (this.nomeProdutoValido(this.produto.nome)) {
        this.produtoService.createProduto(this.produto).subscribe(data => alert('Produto cadastrado com sucesso!'),
        error => alert('Erro ao cadastrar!'));
    }
    else {
      throw alert('Produto não pode estar vazio.');
    }
  }

  nomeProdutoValido(nomeP: string) {
    if (nomeP === '' || /^\s*$/.test(nomeP)) {
      return false;
    }
    else {
      return true;
    }
  }
}
