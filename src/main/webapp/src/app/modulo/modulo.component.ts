import {Component, OnInit} from '@angular/core';
import {Modulo} from '../modulo';
import {ModuloService} from '../modulo.service';
import {saveAs} from 'file-saver';
import { AnimationDurations } from '@angular/material/core';
import { animate } from '@angular/animations';


@Component({
  selector: 'app-create-modulo',
  templateUrl: './modulo.component.html',
  styleUrls: ['./modulo.component.css']
})
export class ModuloComponent implements OnInit {

  userFile: any = File;
  modulo = new Modulo;
  /* RETORNO DE ERROS AO USER */
  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;
  selectedFiles: FileList;
  filename: string;

  constructor(private moduloService: ModuloService) {
  }

  ngOnInit() {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;

    this.validarArquivoCSV();
  }

  onChange(event) {
    this.filename = event.srcElement.files[0].name;
    this.filename = this.filename.substring(this.filename.length - 20);
  }

  onSelectFile(event) {
    this.userFile = event.target.files[0];
  }

  onSubmit() {

  this.validarArquivoCSV();
  }

  downloadFile() {

    this.moduloService.downloadFile().subscribe(data => {
      this.msgErro = 'Inconsistências encontradas, verifique no arquivo abaixo';
      this.erro = true;
      this.sucesso = false;

      saveAs(new Blob([data], {type: 'multipart/form-data'}), 'inconsistencias.txt');

    }, (error) => {
      this.msgSucesso = 'Nenhuma inconsistência encontrada ';
      this.erro = false;
      this.sucesso = true;
    });
  }

  clear() {
    (<HTMLInputElement>document.getElementById('labelFile')).value = undefined;
    this.userFile = (<HTMLInputElement>document.getElementById('labelFile')).value;
    this.filename = '';
  }

  upload() {
    const formData = new FormData();
    formData.append('file', this.userFile);

    this.moduloService.createModulo(formData).subscribe(
      (data) => {
        this.msgSucesso = 'Cadastro realizado com sucesso!';
        this.erro = false;
        this.sucesso = true;
        this.downloadFile();
        this.clear();
      },
      (error) => {
        this.msgErro = 'Não foi possivel fazer o upload do arquivo selecionado';
        this.erro = true;
        this.sucesso = false;
      });
  }

  exportModel() {
    this.moduloService.downloadFile().subscribe(data => {
      this.msgSucesso = 'Siga o modelo esperado na importação';
      this.erro = false;
      this.sucesso = true;

      saveAs(new Blob([data], {type: 'multipart/form-data'}), 'model.csv');
    });
  }

  validarArquivoCSV(){
    if(this.selectedFiles != undefined) {
    if (this.selectedFiles[0].name.substring(this.selectedFiles[0].name.length - 4) != '.csv') {
      (<HTMLInputElement>document.getElementById('labelFile')).value = undefined;
        this.userFile = (<HTMLInputElement>document.getElementById('labelFile')).value;
        this.filename = '';
        this.msgErro = "Arquivo não é esperado, por favor selecione outro";
        this.erro = true;

        console.log(this.modulo.nomeProduto)
      }else{
        this.erro = false;
      }
    }else{
      this.erro = false;
    }
  }
}
