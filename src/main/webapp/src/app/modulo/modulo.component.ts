import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ProdutosService } from '../produtos.service';
import { Produtos } from '../produtos';
import { Modulo } from '../modulo';
import { ModuloService } from '../modulo.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-create-modulo',
  templateUrl: './modulo.component.html',
  styleUrls: ['./modulo.component.css']
})
export class ModuloComponent implements OnInit {


  constructor(private moduloService: ModuloService, private produtoService: ProdutosService) { }

  toppings = new FormControl();
  toppingList: Produtos[];
  public userFile: any = File;
  modulo = new Modulo;

  ngOnInit() {
    this.produtoService.getProdutosList().subscribe(data => {
      this.toppingList = data;
    }, error => {
      console.log(error)
    });
  }

  onSelectFile(event) {
    const file = event.target.files[0];
    this.userFile = file;
  }
  onSubmit() {
    const formData = this.userFile;

    this.moduloService.createModulo(this.modulo.nomeProduto, formData).subscribe((response) => {
      console.log(response);
    });
    console.log(this.modulo.nomeProduto)
  }

  selectClick(topping){
  
    this.modulo.nomeProduto = topping.nome;
      console.log(this.modulo);
  }
}
