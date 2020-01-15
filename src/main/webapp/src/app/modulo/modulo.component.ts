import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ProdutosService } from '../produtos.service';
import { Produtos } from '../produtos';
import { Modulo } from '../modulo';
import { ModuloService } from '../modulo.service';
import { DomSanitizer } from '@angular/platform-browser';
import { saveAs } from 'file-saver';
import { Observable } from 'rxjs';
import { HttpBackend, HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-create-modulo',
  templateUrl: './modulo.component.html',
  styleUrls: ['./modulo.component.css']
})
export class ModuloComponent implements OnInit {


  constructor(private moduloService: ModuloService, private produtoService: ProdutosService, private sanitizer: DomSanitizer) { }

  toppings = new FormControl();
  toppingList: Produtos[];
  userFile: any = File;
  modulo = new Modulo;
  fileUrl;

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

    this.moduloService.createModulo(formData).subscribe(data => this.downloadFile(), error => alert("Deu erro"));

  }

  downloadFile() {

    this.moduloService.downloadFile().subscribe(data => {
      alert('Confirme para exibir o relatorio do cadastro');

      console.log("Passou opor aqui");

      saveAs(new Blob([data], { type: 'multipart/form-data' }), 'error.txt');

    });
  }
}