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

    this.validarArquivoCSV();
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

    this.validarArquivoCSV();

    const formData = new FormData();
    formData.append('file', this.userFile)

    this.moduloService.createModulo(formData).subscribe(
      (data) => {
        this.msgSucesso = 'Cadastro realizado com sucesso!';
        this.erro = false;
        this.sucesso = true;
        this.downloadFile();
        this.limpar();
      },
      (error) => {
        this.msgErro = 'Selecione um arquivo CSV!';
        this.erro = true;
        this.sucesso = false;
      });

  }

  downloadFile() {

    this.moduloService.downloadFile().subscribe(data => {
      this.msgSucesso = 'Baixando as inconsistências encontradas';
      this.erro = false;
      this.sucesso = true;

      saveAs(new Blob([data], {type: 'multipart/form-data'}), 'inconsistencias.csv');

    }, (error) => {
      this.msgErro = 'Nenhuma inconsistência encontrada, upload concluido com sucesso!';
      this.erro = true;
      this.sucesso = false;
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

  limpar() {
    (<HTMLInputElement>document.getElementById('labelFile')).value = undefined;
    this.userFile = (<HTMLInputElement>document.getElementById('labelFile')).value;
    this.filename = '';
  }
}
