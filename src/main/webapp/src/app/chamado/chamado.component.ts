import { Component, OnInit } from '@angular/core';
import { Chamado } from '../chamado';
import { Produtos } from '../produtos';
import { ChamadoService } from '../chamado.service';
import { ProdutosService } from '../produtos.service';
import { FormControl } from '@angular/forms';
import { FileUploader } from "ng2-file-upload";

@Component({
  selector: 'app-chamado',
  templateUrl: './chamado.component.html',
  styleUrls: ['./chamado.component.css']
})
export class ChamadoComponent implements OnInit {

  chamado: Chamado = new Chamado();
  submitted = false;

  public uploader: FileUploader;

  /* LISTA DE PRODUTOS */
  produtos = new FormControl();
  produtoList: Produtos[];

  /* FILE */
  formData: FormData = new FormData();

  /* RETORNO DE ERROS AO USER */
  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  constructor(private chamadoService: ChamadoService, private produtoService: ProdutosService) { }

  /* MÉTODOS DO FILE */

  onSelectFile(event) {
    for (let i = 0; i < this.uploader.queue.length; i++) {
      let fileItem = this.uploader.queue[i]._file;
      if (fileItem.size > 10000000) {
        alert("Each File should be less than 10 MB of size.");
        return;
      }
    }
    for (let j = 0; j < this.uploader.queue.length; j++) {
      let data = new FormData();
      let fileItem = this.uploader.queue[j]._file;
      console.log(fileItem.name);
      data.append('file', fileItem);
      data.append('fileSeq', 'seq' + j);
      this.formData = data;
    }
  }

  ngOnInit() {
    this.produtoService.getProdutosList().subscribe(
      data => {
        this.produtoList = data;
      }, error => {
        console.log(error)
      });
  }

  save() {

    // if (this.chamado.arquivo != null || this.chamado.arquivo === '') {
    //   this.chamado.arquivo = this.chamado.arquivo.substring(12);
    // }
    console.log(this.produtoList)
    this.chamadoService.createChamado(this.chamado, this.formData).subscribe(
      (data) => {
        this.msgSucesso = 'Cadastro realizado com sucesso!';
        this.erro = false;
        this.sucesso = true;
        console.log(this.msgSucesso);
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

    // this.currentFileUpload = this.selectedFiles.item(0);
    // this.chamadoService.uploadFile(this.currentFileUpload).subscribe();

  }

  /* LIMPAR OS CAMPOS APÓS CADASTRO */
  limpar() {
    this.chamado.titulo = '';
    this.chamado.descricao = '';
    this.chamado.produtoList = [];
    (<HTMLInputElement>document.getElementById('labelFile')).value = undefined;
  }

  selectClickProdutos(produtos) {

    const index = this.chamado.produtoList.indexOf(produtos, 0);
    if (index > -1) {
      this.chamado.produtoList.splice(index, 1);
      this.chamado.produtoList.push(produtos);
    }
    console.log(this.chamado.produtoList);
  }

  // verificarFile() {

  //   if (this.selectedFiles != undefined) {
  //     if (this.selectedFiles[0].size > (1000 * 1000 * 2) * 10) {
  //       (<HTMLInputElement>document.getElementById('validatedCustomFile')).value = undefined;
  //       this.chamado.arquivo = (<HTMLInputElement>document.getElementById('validatedCustomFile')).value;
  //       this.filename = '';
  //       this.msgErro = "Arquivo maior que o esperado, por favor, selecione outros";
  //       this.erro = true;

  //     } else {

  //     }

  //     if (this.selectedFiles.length > 10) {
  //       (<HTMLInputElement>document.getElementById('validatedCustomFile')).value = undefined;
  //       this.chamado.arquivo = (<HTMLInputElement>document.getElementById('validatedCustomFile')).value;
  //       this.filename = '';
  //       this.msgErro = "Limite de 10 arquivos.";
  //       this.erro = true;
  //     } else {
  //       this.erro = false;
  //     }
  //   } else {
  //     this.erro = false;
  //   }
  // }
}