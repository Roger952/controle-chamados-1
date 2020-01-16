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

  userFile: any = File;
  modulo = new Modulo;

  /* RETORNO DE ERROS AO USER */
  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  selectedFiles: FileList;
  filename: string;

  ngOnInit() {
  }

  selectFile(event){
    this.selectedFiles = event.target.files;
  }

  onChange(event) {
    this.filename = event.srcElement.files[0].name;
    this.filename = this.filename.substring(this.filename.length - 20);
  }

  onSelectFile(event) {
    const file = event.target.files[0];
    this.userFile = file;
  }

  onSubmit() {

    const formData = new FormData();
    formData.append('file', this.userFile)

    this.moduloService.createModulo(formData).subscribe(
      (data) => {
        this.msgSucesso = 'Cadastro realizado com sucesso!';
        this.erro = false;
        this.sucesso = true;
        this.downloadFile();
      }, 
      (error) => {
        this.msgErro = 'Selecione um arquivo CSV!';
        this.erro = true;
        this.sucesso = false;
    });

  }

  downloadFile() {

    this.moduloService.downloadFile().subscribe(data => {
      alert('Confirme para exibir o relatorio do cadastro');

      saveAs(new Blob([data], { type: 'multipart/form-data' }), 'error.txt');

    });
  }
}