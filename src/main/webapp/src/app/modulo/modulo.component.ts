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
  userFile: any = File;
  modulo = new Modulo;

  ngOnInit() {
  }

  onSelectFile(event) {
    const file = event.target.files[0];
    this.userFile = file;

    console.log(this.userFile)

  }
  onSubmit() {

    const formData = new FormData();

    formData.append('file', this.userFile)

    this.moduloService.createModulo(formData).subscribe((response) => {
      console.log(response);
    });

  }
}
