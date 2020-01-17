import {Component, OnInit} from '@angular/core';
import {Modulo} from '../modulo';
import {ModuloService} from '../modulo.service';
import {saveAs} from 'file-saver';


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
  }

  onChange(event) {
    this.filename = event.srcElement.files[0].name;
    this.filename = this.filename.substring(this.filename.length - 20);
  }

  onSelectFile(event) {
    this.userFile = event.target.files[0];
  }

  onSubmit() {
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
    this.moduloService.downloadModule().subscribe(data => {
      this.msgSucesso = 'Siga o modelo esperado na importação';
      this.erro = false;
      this.sucesso = true;

      saveAs(new Blob([data], {type: 'multipart/form-data'}), 'model.csv');

    });
  }
}

