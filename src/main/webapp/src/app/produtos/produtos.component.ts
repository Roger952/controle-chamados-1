import { Component, OnInit } from '@angular/core';
import { Produtos } from '../produtos';
import { ProdutosService } from '../produtos.service';
import { Router } from '@angular/router';

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

  newProduto(): void{
    this.submitted = false;
    this.produto = new Produtos();
  }

  save(){
    this.produtoService.createProduto(this.produto)
    .subscribe(data => console.log(data), 
    error => console.log(error));
  }

  onSubmit(){
    this.submitted = true;

    if(this.nomeProdutoValido(this.produto.nome)){
      this.save();

      throw alert('Produto cadastrado com sucesso!');
    }
    else{
      throw alert('Produto não pode estar vazio.');
    }
  }

  nomeProdutoValido(nomeP: string){
    if(nomeP === '' || nomeP === undefined || nomeP === null){
      return false;
    }
    else{
      return true;
    }
  }

}
