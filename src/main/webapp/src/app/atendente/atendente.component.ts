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

  /* LISTA DE PRODUTOS */
  produtos = new FormControl();
  produtoList: Produtos[];

  /* FILE */
  selectedFiles: FileList;
  currentFileUpload: File;
  filename: string;

  /* RETORNO DE ERROS AO USER */
  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  constructor(private atendenteService: AtendenteService, private produtoService: ProdutosService) { }

  /* MÉTODOS DO FILE */
  selectFile(event){
    this.selectedFiles = event.target.files;
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
      console.log(error)
    });
  }

  newAtendente(): void {
    this.submitted = false;
    this.atendente = new Atendente();
  }

  save() {

    if(this.atendente.foto != null){
        this.atendente.foto = this.atendente.foto.substring(12);
    }

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
          console.log(this.msgErro);
    });
  }

  onSubmit() {
    this.submitted = true;
    this.save();

    this.currentFileUpload = this.selectedFiles.item(0);
    this.atendenteService.uploadImg(this.currentFileUpload).subscribe();

    console.log("Arquivo (file): "+this.currentFileUpload);
    console.log("Atendente: "+this.atendente);
  }

  /* LIMPAR OS CAMPOS APÓS CADASTRO */
  limpar() {
    this.atendente.nome = '';
    this.atendente.email = '';
    this.atendente.senha = '';
    this.atendente.produtoList = [];

    this.atendente.foto = "C:/fakepath/default-person.png";
    this.filename = ''
  }

  selectClick(produtos){

    const index = this.atendente.produtoList.indexOf(produtos, 0);
    if(index > -1){
      this.atendente.produtoList.splice(index, 1);
      this.atendente.produtoList.push(produtos);
    }
    console.log(this.atendente.produtoList);
  }

}
