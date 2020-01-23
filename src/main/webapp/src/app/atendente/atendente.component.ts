import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Atendente } from '../atendente';
import { AtendenteService } from '../atendente.service';
import { ProdutosService } from '../produtos.service';
import { Produtos } from '../produtos';


@Component({
  selector: 'app-atendente',
  templateUrl: './atendente.component.html',
  styleUrls: ['./atendente.component.css']
})
export class AtendenteComponent implements OnInit {

  atendente: Atendente = new Atendente();
  submitted = false;

  produtos = new FormControl();
  produtoList: Produtos[];

  selectedFiles: FileList;
  currentFileUpload: File;
  filename: string;

  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  constructor(private atendenteService: AtendenteService, private produtoService: ProdutosService) { }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    this.verificarFile();
  }

  onChange(event) {
    this.filename = event.srcElement.files[0].name;
    this.filename = this.filename.substring(this.filename.length - 20);
  }

  ngOnInit() {
    this.produtoService.getProdutosList().subscribe(
      data => {
        this.produtoList = data;
      }, error => {
        console.log(error);
      });
  }

  verificarFile() {

    let formData: FormData = new FormData();

    if(this.selectedFiles != undefined) {
      if (this.selectedFiles[0].size > 1000 * 1000 * 2) {
        (<HTMLInputElement>document.getElementById('labelFile')).value = undefined;
        this.atendente.foto = (<HTMLInputElement>document.getElementById('labelFile')).value;
        this.filename = '';
        this.msgErro = "Arquivo maior que o esperado, por favor selecione outro";
        this.erro = true;
      } else{
        this.erro = false;
      }

      if (this.selectedFiles[0].name.substring(this.selectedFiles[0].name.length - 5) != '.jpeg'
        && this.selectedFiles[0].name.substring(this.selectedFiles[0].name.length - 5) != '.jfif'
        && this.selectedFiles[0].name.substring(this.selectedFiles[0].name.length - 4) != '.jpg'
        && this.selectedFiles[0].name.substring(this.selectedFiles[0].name.length - 4) != '.png') {
        (<HTMLInputElement>document.getElementById('labelFile')).value = undefined;
        this.atendente.foto = (<HTMLInputElement>document.getElementById('labelFile')).value;
        this.filename = '';
        this.msgErro = "Arquivo não é esperado, por favor selecione outro";
        this.erro = true;

      }else{

      }
    }else{
      this.erro = false;
    }
  }

  newAtendente(): void {
    this.submitted = false;
    this.atendente = new Atendente();
  }

  save() {

    if (this.atendente.foto != null || this.atendente.foto === '') {
      this.atendente.foto = this.atendente.foto.substring(12);
    }

    if (this.confirmacaoSenha()) {
      this.msgErro = 'As senhas não correspondem';
      this.erro = true;
      this.sucesso = false;

    } else {
      this.atendenteService.createAtendente(this.atendente).subscribe(
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
        });
    }
  }

  onSubmit() {

    this.verificarFile();
    this.submitted = true;
    this.save();

    this.currentFileUpload = this.selectedFiles.item(0);
    this.atendenteService.uploadImg(this.currentFileUpload).subscribe();
  }

  limpar() {
    this.atendente.nome = '';
    this.atendente.email = '';
    this.atendente.senha = '';
    this.atendente.produtoList = [];
    (<HTMLInputElement>document.getElementById('senhaConfirmacao')).value = '';
    (<HTMLInputElement>document.getElementById('labelFile')).value = undefined;
    this.atendente.foto = (<HTMLInputElement>document.getElementById('labelFile')).value;
    this.filename = '';
  }

  selectClick(produtos) {

    const index = this.atendente.produtoList.indexOf(produtos, 0);
    if (index > -1) {
      this.atendente.produtoList.splice(index, 1);
      this.atendente.produtoList.push(produtos);
    }
  }

  confirmacaoSenha(): boolean {
    const senhaConfirmacao = (<HTMLInputElement>document.getElementById('senhaConfirmacao')).value;

    if (this.atendente.senha != senhaConfirmacao) {

      this.erro = true;
      this.sucesso = false;
      return this.erro;
    }
  }
}
