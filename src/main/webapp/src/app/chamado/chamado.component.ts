import { Component, OnInit } from '@angular/core';
import { Chamado } from '../chamado';
import { Produtos} from '../produtos'; 
import { ChamadoService } from '../chamado.service';
import { EmpresaService } from '../empresa.service';
import { ProdutosService } from '../produtos.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-chamado',
  templateUrl: './chamado.component.html',
  styleUrls: ['./chamado.component.css']
})
export class ChamadoComponent implements OnInit {

  chamado: Chamado = new Chamado();
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

  constructor(private chamadoService : ChamadoService, private produtoService: ProdutosService) { }

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
 
save() {

  if(this.chamado.foto != null){
        this.chamado.foto = this.chamado.foto.substring(12);
    }
    
  this.chamadoService.createChamado(this.chamado).subscribe(
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
}

/* LIMPAR OS CAMPOS APÓS CADASTRO */
limpar() {
  this.chamado.titulo = '';
  this.chamado.descricao = '';
  this.chamado.produtoList = [];
  (<HTMLInputElement>document.getElementById('labelFile')).value = undefined;
  this.chamado.foto = (<HTMLInputElement>document.getElementById('labelFile')).value;
  this.filename = '';
 }

 selectClickProduto(produtos) {

  const index = this.chamado.produtoList.indexOf(produtos, 0);
  if(index > -1){
    this.chamado.produtoList.splice(index, 1);
    this.chamado.produtoList.push(produtos);
  }
  console.log(this.chamado.produtoList);
}
}